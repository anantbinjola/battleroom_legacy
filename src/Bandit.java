package com.payne.game.objects;

import java.util.LinkedList;



import java.awt.Graphics;

import java.awt.Graphics2D;

import com.payne.game.framework.Texture;

import java.awt.Color;

import java.awt.Rectangle;

import com.payne.game.framework.GameObject;

import com.payne.game.framework.KeyInput;





import com.payne.game.window.Animation;

import com.payne.game.window.Game;


import com.payne.game.framework.ObjectId;

import com.payne.game.framework.EnemyId;

public class Bandit extends Enemy
{  
	private int maxPatrolCount=500;//no of pixels in x dir to patrol
	
	private long elapsed;
	
	private int patrolCount=0; 

	private int distance =0;
	
	private GameObject player=null;
	
	float playerX,playerY;

	Texture tex = Game.getInstance();
	
	public Bandit(float x,float y, ObjectId id)
	{	
		
		super(x,y,id,EnemyId.Bandit);
		
		setWidth (32);
		setHeight(48);
		
		setHp(200);
		
		slashingRight= new Animation(3,tex.banditStab[0],tex.banditStab[1],tex.banditStab[2],tex.banditStab[3],tex.banditStab[4],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0],tex.bandit[0]);
		slashingLeft= new Animation(3,tex.banditStab[5],tex.banditStab[6],tex.banditStab[7],tex.banditStab[8],tex.banditStab[9],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5],tex.banditStab[5]);
		playerWalk = new Animation(6,tex.bandit[1],tex.bandit[2],tex.bandit[3],tex.bandit[4],tex.bandit[5],tex.bandit[6]);
		playerWalkLeft = new Animation(6,tex.bandit[7],tex.bandit[8],tex.bandit[9],tex.bandit[10],tex.bandit[11],tex.bandit[12]);
		
		firingTimer=System.nanoTime();
		firingDelay = 450;
	
	}
	
	public void tick(LinkedList<GameObject> object)
	{	
		
		enemyAI();
		
		
		if(isCrouching())
		{
			height=32;
			y=y+8;
			
		}else {	height = 48; }
		if(right)
		{	if(!isCrouching())
			if(velX>=MAX_SPEED)
				velX=MAX_SPEED;
			
			else
				velX= velX+dX;
		
		}
		if(left)
		{	if(!isCrouching())
			if(velX<=-MAX_SPEED)
				velX=-MAX_SPEED;
			else
			
			velX= velX-dX;
		
		}
		
		if(!isCrouching()){
		x +=velX;
		y+=velY;
		
		}
		
		if(velX>0)facingRight = true;
		if(velX<0)facingRight = false;
		
		
		
		if(falling||jumping)
		{	
			velY+=gravity;
			
			if(velY>MAX_FALLSPEED)
			{
				velY=MAX_FALLSPEED;
			}
			
			jumping = true;
		
		}
	
		Collision(object);	
		
		

		if(slashing)
		{
			if(slashingRight.getCount()==4 || slashingLeft.getCount()==4)
			{	
				swordCollision(object);
			
			}
		
		}
		
		slashingRight.runAnimation();		

		slashingLeft.runAnimation();
		
		

		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		
		
		
		
	}
	
	
	
	
	public void enemyAI()
	{
		int diffY=(y<playerY)?(int)(playerY-y):(int)(y-playerY);
		
		
		if(playerX<x-5)	//player is to right
		{ 
			int diff=(int)(x-playerX);
			if(diff>100) { Patrolling(); slashing=false;  }
			else if(diff>20 && diffY<20) {left=true; right=false; facingRight=false; slashing=false; } //move towards player
			
			else   { velX=0; left=false; right=false; facingRight=false; slashing=true; } //slash player
		}
		else
		if(playerX>x+5)//player is to left
		{ 
			int diff=(int)(playerX-x);
			if(diff>100) { Patrolling(); slashing=false; }
			else if(diff>20 && diffY<20) {left=false; right=true; facingRight=true; slashing=false; } //move towards player
			
			else   
			{ velX=0; left=false; right=false; facingRight=true; slashing=true;} //slashing player
		}
		
	}
	
	
	private  void bulletCollision(LinkedList<GameObject> object)
	{
		for(int i=0;i<object.size();i++)
		{
			GameObject tempBullet = object.get(i);
			
			if(tempBullet.getId()==ObjectId.Bullets)
			{
				for(int j=0;j<object.size();j++)
				{
					GameObject tempObject = object.get(j);
					if(tempObject.getId()==ObjectId.Block)
					{
						if(tempBullet.getBounds().intersects(tempObject.getBounds()))
						{
							object.remove(i);
							i--;				
						}
				
					}else
				
					if(tempObject.getId()==ObjectId.Player)
					{					
						if(tempBullet.getBounds().intersects(tempObject.getBounds()))
						{						
							object.remove(i);
							i--;
							
							tempObject.setDamage(true);
							
							if(x>tempObject.getX())
								tempObject.setDamageRight(true);
							else
								tempObject.setDamageRight(false);
							
							int tempHp=tempObject.getHp()-100;
							
							tempObject.setHp(tempHp);
							
							if(tempHp<=0)
							{
								object.remove(j);
								j--;
							}
					
						}
					}
				}
			}
		}
	}
	
	public  void render(Graphics2D g)
	{	
		if(damage)
		{
			jumping = true;
			velX=0;
			
			if(damageRight)
			{
				g.drawImage(tex.damage[0],(int)x,(int)y,null);
			}
			else
			{	
				
				g.drawImage(tex.damage[1],(int)x,(int)y,null);
				
			}
			
			cnt++;
			
			if(cnt==30)
			{
				damage = false;
				cnt=0;
			}
			
		}
		else
		if(slashing&&velY==0)
		{	
			if(facingRight)
			{
				
				slashingRight.drawAnimation(g,(int)x,(int)y);
				
				
			
			}
			else
			{
				slashingLeft.drawAnimation(g,(int)(x-width/2),(int)y);
				
			}
			velX=0;
			velY=0;
			
			
		}else
		
		if(!isCrouching())
		if(velY<0)
		{	falling=true;
			if(facingRight)
				g.drawImage(tex.banditJump[0],(int)x,(int)y,null);
			else if(!facingRight)
				g.drawImage(tex.banditJump[1],(int)x,(int)y,null);
			
			
			
			
			
			
			
			
		}else 	if(velY>0)
			{	
			if(facingRight)
				g.drawImage(tex.banditFall[0],(int)x,(int)y,null);
			
			else 
				g.drawImage(tex.banditFall[1],(int)x,(int)y,null);
			
			
			}else
		
	
			if(velX!=0 )
		{	
			if(facingRight)
			{	playerWalk.drawAnimation(g,(int)x,(int)y);
				
			}
			else 
			{	playerWalkLeft.drawAnimation(g,(int)x,(int)y);
				
			}
			
			
		}else
			
		{
			if(facingRight)
			{ 
				
				g.drawImage(tex.bandit[0],(int)x,(int)y,null);
				
			}
			else if(!facingRight)
				{
				
				if(isCrouching())
				{	
					g.drawImage(tex.crouch[1],(int)x,(int)y,null);
				}else {
				
					g.drawImage(tex.bandit[13],(int)x,(int)y,null);
				}
				
				}
			
		}
		else{
		
		if(isCrouching()&&facingRight)
				{	
					g.drawImage(tex.crouch[0],(int)x,(int)y,null);
					
			 }else if(isCrouching()&& !facingRight)
				{	
					g.drawImage(tex.crouch[1],(int)x,(int)y,null);
				}
		
		
		}
		
		
		if(slashing)
		{
			if(slashingRight.getCount()>=4 || slashingLeft.getCount()>=4)
			{	
				g.draw(getswordBounds());
			
			}
		
		}
		
		g.setColor((new Color(90,214,5)));
		g.fillRect((int)x,(int)y-10,(int)(hp/5),10);					
		
		
		
	}
	

	
	

	public Rectangle getswordBounds()
	{	
		if(facingRight)
	
			return new Rectangle((int)(x+width+3),(int)(y+22),8,5) ;//swordRight
		
		else
		
			return new Rectangle((int)(x-10),(int)(y+22),8,5) ;	//swordLeft
	
	
	}

	

	
	
	
	private  void swordCollision(LinkedList<GameObject> object)
	{
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId()==ObjectId.Player)
			{
				if(getswordBounds().intersects(tempObject.getBounds()))
				{
					int tempHp=tempObject.getHp()-2;
					tempObject.setHp(tempHp);
					if(tempHp<=0)
					{
						object.remove(i);
						i--;
					}
				}
				
			}
		}
	}
	
	
	
	
	
	
	
	
	public void Patrolling()
	{	
		if(patrolCount>0) 
			{
			patrolCount-=(velX>0)?velX:-velX;
			}
		else
		{
			
			patrolCount=maxPatrolCount;
			if(left){ left=false; right=true;}
			else if(right) { left=true; right=false; }
			
		}
		
	
	
	}
	
	
	
	
	
	
	
	
	
	
	public void Collision(LinkedList<GameObject> object)
	{
		
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Player)
			{	player=tempObject;
				playerX=player.getX();
				playerY=player.getY();
			}
				
			
			if(tempObject.getId() == ObjectId.Block)
			{	
				//UP
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{	y=tempObject.getY() + 32;
					velY=0;		
				}
				
				//DOWN
				if(getBoundsBottom().intersects(tempObject.getBounds()))
				{	falling=false;
					y=tempObject.getY() - height+1;
					velY=0;	
					jumping=false;
				}
				else
				{
					// jumping=true;
					falling = true;
				}
				
					//RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{	
					x=tempObject.getX() - width-1.5f;
					velX=0;
					
					
					
					
					

				}else
				
				//left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{		
					x=tempObject.getX() + width + 0.5f;
					
					y=y-0.5f;
					
					
					
					
					
					
				}	
				
			}
			if(tempObject.getId() == ObjectId.Enemy&& this!=tempObject)
			{
				if(this.getBounds().intersects(tempObject.getBounds()))
				{
					float tempX=tempObject.getX();
					if(x<tempX)
					{
						x=tempObject.getX() - width+0.5f;
						
					}
					else 
					{
						x=tempObject.getX() + width + 0.5f;
					}
					
					this.setVelX(0);
					
					tempObject.setVelX(0);
					
				} 
				
				
			}
			
		}
	}
	
	
	
	
	public Rectangle getBounds()
	{			
		return new Rectangle((int)x ,(int)y,(int)width,(int)height);
	}
	
	
	
	
	
	public Rectangle getBoundsBottom()
	{	
		
		
		return new Rectangle((int)((int)x + (width/2)-((width/2)/2)) ,(int)((int)y + height/2),(int)(int)width/2,(int)height/2);
	}
	
		
		public Rectangle getBoundsTop()
	{
		return new Rectangle((int)((int)x + (width/2)-((width/2)/2)),(int)y,(int)(int)width/2,(int)height/2);
	}
		
		public Rectangle getBoundsRight()
	{	
	
		return new Rectangle((int)((int)x + width - 5),(int)y+2,(int)(5+((velX>0)?velX:-velX)),(int)height-5);
	}
	
		public Rectangle getBoundsLeft()
	{
		return new Rectangle((int)(x-((velX>0)?velX:-velX)),(int)y+2,(int)(5+((velX>0)?velX:-velX)),(int)height-5);
	}

		

	


}