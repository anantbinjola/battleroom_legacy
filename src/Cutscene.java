package com.payne.game.framework;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.Font;

import java.awt.FontMetrics;

import com.payne.game.framework.KeyInput;

import com.payne.game.window.Game;


public class Cutscene
{

	public static boolean select=false;

	public static int index=0;
	
	private float opacity = 255;

	
	private float x=800f,y=300f,size = 1;

	private int token=1;
	
	public static boolean skip;
	
	private String s, s1,s2,s3,s4;
	
	private boolean fade = false;
	
	Texture tex = Game.getInstance();
	
	public Cutscene()
	{
		
		
		
	}
	
	public void render(Graphics2D g)
	{
		
		
		if(Game.win)
		{
			g.fillRect(0,0,800,800);
			g.setColor(Color.BLUE);
			Font font = new Font("Courier",Font.BOLD,70);
			g.setFont(font);
			g.drawString("YOU WIN",100,100);
			g.scale(3,3);
			
		}else
		if(token==1)
		{
			s1 = "PAYNE";
			s2 = "AND";
			s3 = "NITROX";
			s4 = "PRODUCTIONS";
			
			
			
		//	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));	//fade logic
			
			g.setColor(Color.CYAN);
			g.fillRect(0,0,800,800);
			g.setColor(new Color(255,255,255,(int)opacity));
			Font font = new Font("Courier",Font.BOLD,(int)size);
			g.setFont(font);
			
			FontMetrics fm = g.getFontMetrics();
			
			g.drawString(s1,(int)(800/2 - fm.getStringBounds(s1,g).getWidth()/2),(int)(600/2- fm.getStringBounds(s1,g).getHeight()/2) - size);
			g.drawString(s2,(int)(800/2 - fm.getStringBounds(s2,g).getWidth()/2),(int)(600/2- fm.getStringBounds(s2,g).getHeight()/2) );
			g.drawString(s3,(int)(800/2 - fm.getStringBounds(s3,g).getWidth()/2),(int)(600/2- fm.getStringBounds(s3,g).getHeight()/2) + size);
			g.drawString(s4,(int)(800/2 - fm.getStringBounds(s4,g).getWidth()/2),(int)(600/2- fm.getStringBounds(s4,g).getHeight()/2) + size*2);
			
			
			
			
			
			if(opacity >0)
			{
				
				fade = true;
			}
			else
			{
				opacity = 255;
				fade = false;
			}
			
			if(fade)
			{
				if(size<70)
				{
					
					size = size + 0.1f;
			
				}else
					opacity = opacity - 0.3f;
				
			}else
			{
				opacity = 255;
				token = 2;
				fade = true;
			}
			
			
			
			
		
		}
		else if(token == 2)
		{
			s="THE NIGHTCRAWLER";
			
			//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));	//fade logic
			
			g.setColor(Color.RED);
			g.fillRect(0,0,800,800);
			g.setColor(new Color(0,0,0,(int)opacity));
			Font font = new Font("Courier",Font.BOLD,75);
			g.setFont(font);
			
			FontMetrics fm = g.getFontMetrics();
			
			
			g.drawString(s,(int)(800/2 - fm.getStringBounds(s,g).getWidth()/2),(int)(600/2- fm.getStringBounds(s,g).getHeight()/2)+75);
			
			if(opacity >0)
			{
				
				fade = true;
			}
			else
			{
				fade = false;
			}
			
			
			if(fade)
			{
				
				opacity = opacity - 0.5f;
				
				
			}else
			{
				opacity = 255;
				skip = true;
				
			}
			
		}
		
		
		
		index++;
		
		if(index > 10000)
			Game.state=Game.STATE.GAME;
		else
		if(skip)
		{
			if(Game.win)
			{
				Game.state=Game.STATE.MENU;
			
			}else
			{
				Game.state=Game.STATE.GAME;
			}
			
			
		}
		
		
		
		
	}
	
	public void tick()
	{
		
		
		
		
		
		
		
		
		
		
	}

	

}