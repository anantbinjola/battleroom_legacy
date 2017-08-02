package com.payne.game.window;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import com.payne.game.framework.KeyInput;
import com.payne.game.window.BufferedImageLoader;
import com.payne.game.framework.Texture;
import com.payne.game.framework.ObjectId;
import com.payne.game.framework.EnemyId;
import com.payne.game.framework.Menu;
import com.payne.game.framework.Cutscene;
import com.payne.game.framework.GameObject;
import com.payne.game.objects.Block;
import com.payne.game.objects.Enemy;
import com.payne.game.objects.Bandit;
import com.payne.game.objects.Archer;
import com.payne.game.objects.BulletType1;
import com.payne.game.objects.Player;

public class Game extends Canvas implements Runnable
{
	
	/**
		@param running		: 	Tells that the main Game thread is in RUNNING state
		@param isStarted	: 	Tells us wether the Game has started or not
		@param reset		: 	tells to restart the Game
		@param mapWidth		: 	Width of the Map
		@param mapHeight	:	Height of the Map
		@param level		:	The current level's BufferedImage
		@param bg1_0		:	The fixed background for current level
		@param bg1_2		:	The first paralax background for current level
		@param bg1_3		:	The second paralax background for current level which can also be the working background i.e. the physical ground
		@param player_hud	:	The image that works as the hud for the player
		@param tempObject	:	An object of GameObject Type for storing the player object for checking the hp,currentPosition(i.e. x,y) etc
		@param WIDTH		:	Canvas Width
		@param HEIGHT		:	Canvas Height
		@param minX,minY	:	minimum X,Y
		@param maxX,maxY	:	maximum X,Y
		@param handler		: 	Object of type Handler
		@param menu			:	Object of type Menu
		@param cam			:	Object of type Camera
		@param tex			:	Object of type Texture
		@param currentLevel	:	String containing current level info
		@param loader		: 	Object of type BufferedImageLoader which returns the BufferedImage at a path
		
	*/
	
	private boolean running=false;												
	
	public static boolean isStarted=false;
	
	public static boolean reset=false, win = false;
	
	public BufferStrategy bs;
	
	private Thread thread;
	
	private boolean battle = false, waveStart = false;
	
	private int wave = 1;
	
	
	
	private long waveStartTimer;
	
	private long waveStartTimerDiff;
	
	private int waveDelay = 2000;
	
	
	
	
	private int mapWidth,mapHeight;
	
	private BufferedImage level = null,bg1_0=null,bg1_2=null,bg1_3=null,up = null, battleroom = null,br_bg1_3 = null,waveImage = null;

	public BufferedImage player_hud=null;
	
	private GameObject tempObject=null;
	
	public static int WIDTH,HEIGHT,minX, minY,maxX=0,maxY=0;
	
	public static Handler handler;
	
	private Menu menu = null;
	
	private Cutscene cutscene = null;
	
	Camera cam;
	
	static Texture tex;
	
	
	
	static int ups = 0;
	
	public static String currentLevel = "level1";
	
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	public static int getTick()
	{
		
		return ups;
		
	}
	
	public void restart()												//Function to restart the current level
	{
		//KeyInput.response = true;
		if(battle)
		{
			handler.clear();
			
			loadWave(0);
			
			if(reset)
			{
				wave = 1;
				loadWave(1);
			}else
			{
				loadWave(wave);
			}
			
			
			
			
		}else
		{
			handler.clear();												//Clears the linkedlist of GameObject which Holds all the Objects
			
			loadImageLevel(level);											//loads the level level
		
		}
		
		reset=false;
	}
	
	public void changeLevel()
	{	
		handler.clear();
		
		level = loader.loadImage("/levels/"+currentLevel+"/level.png");	//loading the current level
		
		bg1_0=loader.loadImage("/levels/"+currentLevel+"/bg1_0.png");	//first parallax layer
		
		bg1_3=loader.loadImage("/levels/"+currentLevel+"/bg1_3.png");	//third parallax layer
		
		
		
		
		loadImageLevel(level);											//loads the level currentlevel
	
	}
	
	
	

	public enum STATE													//enumeration for the possible states
	{
		MENU,															//MENUSTATE
		GAME,															//GAMESTATE
		CUTSCENE,														//CUTSCENE
		BULLETHELL,														//BULLETHELL
		PAUSE															//PAUSE
		
	};
	
	public static STATE state = STATE.MENU;								//Static object of STATE enum with default value as MENU
	
	
	private void init()													//INITIALISATION of all the variables for this class
	{	
		//////////////////////////////////////		SETTING THE OUT VARIABLE			//////////////////////////////	
		try
		{			
			PrintStream out = new PrintStream(new File("debugInfo.txt"));
			
			System.setOut(out);
		
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		
		}	
		
		waveStartTimer = 0;
		waveStartTimerDiff = 0;
		
	
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
	
		level = loader.loadImage("/levels/"+currentLevel+"/level.png"); //loading default level Image
		bg1_0=loader.loadImage("/levels/"+currentLevel+"/bg1_0.png");	//first parallax layer
		bg1_3=loader.loadImage("/levels/"+currentLevel+"/bg1_3.png");	//second parallax layer
		up = loader.loadImage("/levels/level1/up.png");
		
		player_hud=loader.loadImage("/HUD/player_hud.png");					//loading the HUD
		
		handler= new Handler();											//initializing the Handler
		
		cam = new Camera(0,0);											//initializing Camera with 0,0 x y
		
		loadImageLevel(level);											
		
		mapWidth = bg1_3.getWidth()-400;								//Width of Map
		mapHeight = bg1_3.getHeight()-300;								//Height of Map
		
		minX = -mapWidth*2;												//calculating minX of cam
		minY= -mapHeight*2 	; 											//calculating minY of cam
		
		
		menu = new Menu();												//Initializing the Menu
		
		cutscene = new Cutscene();
		
		this.addKeyListener(new KeyInput(handler));						//Adding the KeyListner to implement KeyInput
	
	}
	

	public synchronized void start()									//Starting the Game Thread
	{	
		if(running)														
			return;
	
		running = true;
		
		thread = new Thread(this);
		thread.start();
	
	}
	
	public void run()
	{																	//Starting of the game loop
		init();															
		this.requestFocus();											
		long lastTime = System.nanoTime();								
		double amountOfTicks = 60.0;									
		double ns = 1000000000 / amountOfTicks;							
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1)
			{
				if(state == STATE.GAME)
					tick();
				else
				if(state == STATE.MENU)
					menu.tick();
				else
				if(state == STATE.CUTSCENE)
					cutscene.tick();
				
				updates++;
				
				delta--;
				
				ups = updates;
			
			}
				
			render();
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
				
			}
		}																		//End of Game Loop
	}
	
	private void tick()
	{
		isStarted=true;
		
		handler.tick();
		
		for(int i = 0; i < handler.object.size(); i++)
		{	
			if(handler.object.get(i).getId()==ObjectId.Player)
			{	
				cam.tick(handler.object.get(i));
			}
			
			GameObject temp = handler.object.get(i);
			
			if((temp.getX()>650 || temp.getY()>850|| temp.getX()<maxX || temp.getY()<maxY) && temp.getId()!=ObjectId.Player)
			{	
				handler.removeObject(temp,i);
			}
			
			
		}
		
		
	}
	
	private void render()
	{	
		
	
	
		
	
	
		bs = this.getBufferStrategy();							//get Current Buffer Strategy
	
		if(this.getBufferStrategy()==null)										//checking if no previous BuffersStrategy is applied
		{		
			this.createBufferStrategy(3);										//Number of Buffers to create
		
			return;
		}
		
		Graphics g= bs.getDrawGraphics();										//get Graohics
		
		
		
		
		Graphics2D g2d = (Graphics2D)g;											
		
		
		
		if(wave>5)
		{
			win = true;
			state = STATE.CUTSCENE;
		}

		
		
		
		
		
		
		
		if(state == STATE.CUTSCENE)
		{
	
			cutscene.render(g2d);
			
		}
		else if(state== STATE.GAME||state==STATE.MENU)
		{
		
			///////////////DRAW HERE///////////
			
			if(!battle)
			{
				g2d.drawImage(bg1_0,0,0,800,600,this);									//drawing first parallax layer
			}
			
			g2d.translate(cam.getX(),cam.getY());									//begining of camera
			
			
			
			for(int xx =0; xx < level.getWidth(); xx++)											//traversing all pixels in x axis
			{	
			for(int yy =0; yy < level.getHeight(); yy++)										//traversing all pixels in y axis
			{
				int pixel = level.getRGB(xx,yy);								
				int red = (pixel >> 16) &  0xff;
				int green = (pixel >> 8) &  0xff;
				int blue = (pixel) &  0xff;
				
				if(red == 128 && blue == 128 && green == 128)
				{	
					g2d.drawImage(up,xx*32*2,yy*32*2,300,60,this);
				
				}
				
			}
			}
			
			
	
			g2d.scale(2,2);
			
			if(battle)
			{
				
				g2d.drawImage(bg1_0,0,0,this);									//drawing first parallax layer
			}
			
			if(state==STATE.GAME)													//checking the current state for GAMESTATE
			{	
				handler.render(g2d);												//rendering the handler if state is GAME
		
		
			}
	
			g2d.drawImage(bg1_3,0,0,this);
		
			Graphics temp = bs.getDrawGraphics();									//temp Graohics2D for the usage with Player
			Graphics2D temp1=(Graphics2D)temp;
			temp1.setColor(Color.RED);
			temp1.fillRect(99,48,(int)tempObject.getHp()/5+5,15);					//Health Bar aka HP
			temp1.drawImage(player_hud,32,32,null);									//Player_HUD
			
			
			
			
			
			
			Graphics2D f = temp1;
			
			Graphics2D enemy = temp1;
			//f.setColor(Color.BLUE);
			if(waveStartTimer!=0)
			{
				f.setFont(new Font("Century Gothic", Font.PLAIN,20));

				String s = " - W A V E - " + wave ;

				int length = (int)f.getFontMetrics().getStringBounds(s,f).getWidth();
				
				int alpha = (int)(255 * Math.sin(3.14*waveStartTimerDiff/waveDelay));
				
				if(alpha>255) alpha = 255;
				
				if(alpha < 10)	alpha = 10;
				
				f.setColor(new Color(255,255,255,alpha));
				
				f.drawString(s,400 - length/2,300 );
				

				

				
				
			}
			
			enemy.setColor(Color.WHITE);
			
			enemy.setFont(new Font("Century Gothic", Font.BOLD,20));
			
			enemy.drawString(" enemies -"+ handler.noE, 790 - (int)enemy.getFontMetrics().getStringBounds(" enemies -"+ handler.noE,temp1).getWidth(),30);
		
			if(KeyInput.weaponIndex==0)												//Check for GUN
			{
				temp1.drawImage(tex.weaponIcon[0],110,66,null);						//draw GUN
				
				Graphics2D faded = temp1;											//temp faded graphics for the non working weapon
			
				faded.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));	//fade logic
				
				faded.drawImage(tex.weaponIcon[1],145,70,25,25,null);				//drawing the faded weapon
			}
			else if(KeyInput.weaponIndex==1)										//check for sword
			{
				temp1.drawImage(tex.weaponIcon[1],110,66,null);						//draw sword
				Graphics2D faded = temp1;											//temp faded graphics for the non working weapon
				faded.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));	//faded graphics
				faded.drawImage(tex.weaponIcon[0],145,70,25,25,null);				//drawing the faded weapon
			}

			g2d.translate(-cam.getX(),-cam.getY()); 								//ending the camera
		
		
			Graphics2D gameover =(Graphics2D) bs.getDrawGraphics();
			
			if(tempObject.getHp()<=0)												//checking if the player's health is over
			{
				
				restart();															//restarting the level
			
			}
			
			
			if(reset)																//if selected item in menu is RESTART
			{
				restart();															//restarting the level
			}
			
			
			if(tempObject.getX()>1950 && tempObject.getY()>689)					//end of level 1
			{
				currentLevel="level2";												//changing the current level to level2
				
				changeLevel();														//reinitializing the parameters
				
				mapWidth = bg1_3.getWidth()-400;									//Width of Map
				mapHeight = bg1_3.getHeight()-300;									//Height of Map
			
				minX = -mapWidth*2;													//calculating minX of cam
				minY= -mapHeight*2;													//calculating minY of cam
			
			}
			
			
			if(state == STATE.MENU)													//Checking the state for MENUSTATE
				menu.render(gameover);												//rendering the MENU
			
			}
			
		if(state == STATE.BULLETHELL)
			{
				battle = true;
				waveStart = true;
				wave=1;
				waveStartTimer = System.nanoTime();
				currentLevel="battleroom";												//changing the current level to level2
				if(waveStart)
				changeLevel();														//reinitializing the parameters
				
				mapWidth = bg1_3.getWidth()-400;									//Width of Map
				mapHeight = bg1_3.getHeight()-300;									//Height of Map
			
				minX = -mapWidth*2;													//calculating minX of cam
				minY= -mapHeight*2;													//calculating minY of cam
				state = STATE.GAME;
			}
			
		
		if(battle)
		{
			if(waveStartTimer ==0 && handler.noE ==0 )
			{
				
				
				waveStart = false;
			
				waveStartTimer = System.nanoTime();
			
				wave++;
				
				
			
			
				
			
			}else
			{
				waveStartTimerDiff = (System.nanoTime() - waveStartTimer)/1000000;
				
				if(waveStartTimerDiff > waveDelay)
				{
					waveStart = true;
					waveStartTimer = 0;
					waveStartTimerDiff = 0;
				}
			}
			
			if(waveStart && handler.noE == 0)
			{
				if(wave<=5)
				{
					loadWave(wave);
				
				}else 
				{
					state = STATE.PAUSE;
				}
			}
			
		}	
		
		
		
		
		
		g.dispose();
		g2d.dispose();
		bs.show();																//showing the BufferStrategy
		
	}
	
	
	
	private void loadWave(int wave)
	{
		
		
		String currentWave = "wave" + wave;
		
	
		waveImage = loader.loadImage("/levels/battleroom/waves/"+currentWave+"/level.png");
		
		
		
		
		loadImageLevel(waveImage);
		
		waveStart = true;
	}
	
	
	private void loadImageLevel(BufferedImage image)							//creating the objects to be used in the level
	{	
		int w = image.getWidth();												//width of the level
		int h = image.getHeight();												//height of the level
		
		
		
		for(int xx =0; xx < h; xx++)											//traversing all pixels in x axis
		{	
			for(int yy =0; yy < h; yy++)										//traversing all pixels in y axis
			{
				int pixel = image.getRGB(xx,yy);								
				int red = (pixel >> 16) &  0xff;
				int green = (pixel >> 8) &  0xff;
				int blue = (pixel) &  0xff;
		
				
				
				
		
		
				if(red == 255 && green == 255 && blue == 255)
				{
					
					handler.addObject(new Block(xx*32,yy*32,0,ObjectId.Block));	//adding the block at position xx*32, yy*32
				}
				
				if(red == 255 && green == 0 && blue == 0)
				{
					
					handler.addObject(new Archer(xx*32,yy*32,ObjectId.Enemy));	//adding the Enemy at position xx*32, yy*32
				}
				
				
				
				
				
				if(red == 0 && green == 255 && blue == 0)
				{
					
					
					handler.addObject(new Bandit(xx*32,yy*32,ObjectId.Enemy));	//adding the Enemy at position xx*32, yy*32
				}
				
				if(red == 0 && green == 0 && blue == 255)
				{
					tempObject=new Player(xx*32,yy*32,ObjectId.Player);			//adding the player at position xx*32, y*32
					handler.addObject(tempObject);								//storing the player in tempObject
				}
				
				
				
			}
			
		}
		
	}

	
	
	
	public static Texture getInstance()											//getting the textures for th game
	{
		return tex;
	}
	
	public static void main(String[] args)
	{
		try
		{
			new Window(800,600,"nitrox_payne_test_prototype1",new Game());			//creating the frame through a manually defined class window
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
