package com.payne.game.objects;

import java.util.LinkedList;



import java.awt.Graphics;

import java.awt.Graphics2D;

import com.payne.game.framework.Texture;

import java.awt.Color;

import java.awt.Rectangle;

import com.payne.game.framework.GameObject;
import com.payne.game.framework.KeyInput;



import com.payne.game.framework.BulletId;

import com.payne.game.window.Animation;

import com.payne.game.window.Game;


import com.payne.game.framework.ObjectId;

import com.payne.game.framework.EnemyId;

public class Archer extends Enemy
{  
	private int maxPatrolCount=500;//no of pixels in x dir to patrol
	
	private long elapsed;
	
	float arrowX = 0,arrowY=0;
	
	private float u;

	
	
	private float range;
	
	private float ydiff;
	
	private int direction = 0;		
	

	
	private int patrolCount=0; 

	private int distance =0;
	
	private int weaponIndex = 0;
	
	private Animation shootingRight45,shootingLeft45;
	
	private GameObject player=null;
	
	float playerX,playerY;

	Texture tex = Game.getInstance();
	
	public Archer(float x,float y, ObjectId id)
	{	
		
		super(x,y,id,EnemyId.Archer);
		
		setWidth (32);
		setHeight(48);
		
		setHp(200);
		
		shootingRight= new Animation(5,tex.archerShoot0[0],tex.archerShoot0[1],tex.archerShoot0[2],tex.archerShoot0[3],tex.archerShoot0[4],tex.archerShoot0[5],tex.archerShoot0[6],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7],tex.archerShoot0[7]);
		shootingLeft= new Animation(5,tex.archerShoot0[8],tex.archerShoot0[9],tex.archerShoot0[10],tex.archerShoot0[11],tex.archerShoot0[12],tex.archerShoot0[13],tex.archerShoot0[14],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15],tex.archerShoot0[15]);
		
		
		shootingRight45= new Animation(5,tex.archerShoot45[0],tex.archerShoot45[1],tex.archerShoot45[2],tex.archerShoot45[3],tex.archerShoot45[4],tex.archerShoot45[5],tex.archerShoot45[6],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7],tex.archerShoot45[7]);
		shootingLeft45= new Animation(5,tex.archerShoot45[8],tex.archerShoot45[9],tex.archerShoot45[10],tex.archerShoot45[11],tex.archerShoot45[12],tex.archerShoot45[13],tex.archerShoot45[14],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15],tex.archerShoot45[15]);

		
		playerWalk = new Animation(6,tex.archer[1],tex.archer[2],tex.archer[3],tex.archer[4],tex.archer[5],tex.archer[6]);
		playerWalkLeft = new Animation(6,tex.archer[7],tex.archer[8],tex.archer[9],tex.archer[10],tex.archer[11],tex.archer[12]);
		
		firingTimer=System.nanoTime();
		firingDelay = 450;
		
		weaponIndex = 3;
	
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
			if(shooting)
				velX=0;
			else
				velX= velX+dX;
		
		}
		if(left)
		{	if(!isCrouching())
			if(velX<=-MAX_SPEED)
				velX=-MAX_SPEED;
			else
			if(shooting)
				velX=0;
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
		
		}
	
		Collision(object);	
		
		
		if(shoot)
		{
			bulletCollision(object);
			
		}
		
		shootingRight.runAnimation();
		shootingLeft.runAnimation();
		
		shootingRight45.runAnimation();
		shootingLeft45.runAnimation();
		
		

		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		
		if(shooting&&velY==0)
		{	
			if(shootingRight.getCount()==6||shootingLeft.getCount()==6||shootingRight45.getCount()==6||shootingLeft45.getCount()==6)
			{	
					long elapsed = (System.nanoTime()- firingTimer)/1000000;
					if(elapsed>firingDelay)
				{try{
				
					arrowX = x;
					arrowY = y;
					
					if(direction == 45)
					{
						arrowX+=width/2-1;
						arrowY+=height/2-1;
					}
					else if(direction == 0)
					{
					
						if(!facingRight)
						{
							arrowX+=width/2-10;
							arrowY+=height/2-10;
						}else
						{
							arrowX+=width/2+10;
							arrowY+=height/2-10;
						}
						
					}
					
					
					
					u = 21;
					range = (playerX + 16) - arrowX;
					ydiff = arrowY - (playerY + 5);	
					direction = 0;
					
					range=(float)Math.abs(range);

					float u2=-1;
		
					if(ydiff!=0)
					{
						u2 = -0.5f*gravity*range*range/ydiff;
					}
		
					if(u2>0)
					{
						u = (float)Math.sqrt(u2);
					}
					else
					{	
						direction=45;
					
					}
		
					if(u>20f)
					{
						direction=45;
					}
					
					if(direction == 45)
					{
						if(range>ydiff)
						{
							u = (float)Math.sqrt((gravity*range*range)/(range-ydiff));
						}
						else
						{
							u = 21;
						}
						
						
					}
					
					
					
					if(u<=20)
					{	
						if(weaponIndex==3)
						{
					
							Game.handler.addObject(new Arrow(arrowX,arrowY,ObjectId.Bullets,facingRight,u,direction));
					
						}	
					}
					
					
					
					firingTimer = System.nanoTime();
					
				}catch(Exception e)
				{
				
				}
				shoot=true;
				}
				 
			}
		}
		
		
	
		
	}
	
	
	
	
	public void enemyAI()
	{
	
		int diffY=(y<playerY)?(int)(playerY-y):(int)(y-playerY);
		if(playerX<x)	//player is to right
			{ 
			int diff=(int)(x-playerX);
			if(diff>300) { Patrolling();shooting=false; }
			//else if(diff>200 || diffY>100) {left=true; right=false; facingRight=false; shooting=false;} //move towards player
			else if(diff>0) { velX=0; left=false; right=false; facingRight=false; shooting=true; } //shoot player
			
			}
		else		//player is to left
			{ 
			int diff=(int)(playerX-x);
			if(diff>300) { Patrolling(); shooting=false; }
			//else if(diff>200 || diffY>100) {left=false; right=true; facingRight=true;shooting=false;} //move towards player
			else if(diff>0) { velX=0; left=false; right=false; facingRight=true; shooting=true; } //shoot player
			
			}
		
	}
	
	
	private  void bulletCollision(LinkedList<GameObject> object)
	{
		for(int i=0;i<object.size();i++)
		{
			GameObject tempBullet = object.get(i);
			
			if(tempBullet.getId()==ObjectId.Bullets&&tempBullet.getBulletId()==BulletId.Arrow)
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
					if(tempObject.getId()==ObjectId.Enemy)
					{					
						
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
							
							int tempHp=tempObject.getHp()-10;
							
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
	


	public Rectangle getswordBounds()
	{			if(facingRight)
	
			return new Rectangle((int)(x+width-10),(int)(y+10),25,10) ;	//swordRight
		
		else
		
			return new Rectangle((int)(x-15),(int)(y+10),25,10) ;	//swordLeft
	
	
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
					jumping=true;
					falling = true;
				}
				
				//RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{	x=tempObject.getX() - width+1;
					velX=0;
					velY=-10;   //patrolJump	
				}
				
				//left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{		
					x=tempObject.getX() + width + 0.5f;
					y=y-0.5f;
					velY=-10;	//patrolJump		
				}	
			}
			if(tempObject.getId() == ObjectId.Enemy&& this!=tempObject)
			{
				if(this.getBounds().intersects(tempObject.getBounds()))
				{
					float tempX=tempObject.getX();
					if(x<tempX)
						x=tempObject.getX() - width+1;
					else 
						x=tempObject.getX() + width + 0.5f;
					
					this.setVelX(0);
					
				}
			}
		}
	}
	
	
	public  void render(Graphics2D g)
	{	
		
		//g.drawRect((int)(arrowX),(int)(arrowY),3,3);
		//g.drawString("height "+height,(int)x,(int) y-64);
		//g.drawString("width = "+(width),(int)x,(int) y-32);
		//g.drawString("\nu = "+ u ,(int)x,(int) y);
		
		
		
		//g.drawRect((int)(arrowX+((facingRight)?range:-range)),(int)(arrowY-ydiff),3,3);
		
		
		
		
		
		
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

		if(shooting&&velY==0)
		{
			
			if(facingRight)
			{
				if(direction == 0)
					shootingRight.drawAnimation(g,(int)x,(int)y);
				
				if(direction == 45)
					shootingRight45.drawAnimation(g,(int)x,(int)y);
					
				
			
			}
			else
			{
				if(direction == 0)
					shootingLeft.drawAnimation(g,(int)(x-width/2),(int)y);
				
				if(direction == 45)
					shootingLeft45.drawAnimation(g,(int)(x-width/2),(int)y);
			}
			velX=0;
			velY=0;
			
		}else		
		if(!isCrouching())
		if(velY<0)
		{	falling=true;
			if(facingRight)
				g.drawImage(tex.archerJump[0],(int)x,(int)y,null);
			else if(!facingRight)
				g.drawImage(tex.archerJump[1],(int)x,(int)y,null);
		
		}else 	if(velY>0)
			{	
			if(facingRight)
				g.drawImage(tex.archerFall[0],(int)x,(int)y,null);
			
			else 
				g.drawImage(tex.archerFall[1],(int)x,(int)y,null);
			
			
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
				
				g.drawImage(tex.archer[0],(int)x,(int)y,null);
				
			}
			else if(!facingRight)
				{
				
				if(isCrouching())
				{	
					g.drawImage(tex.crouch[1],(int)x,(int)y,null);
				}else {
				
					g.drawImage(tex.archer[13],(int)x,(int)y,null);
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