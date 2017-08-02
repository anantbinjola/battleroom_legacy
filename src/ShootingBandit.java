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

public class ShootingBandit extends Enemy
{  
	private int maxPatrolCount=500;//no of pixels in x dir to patrol
	
	private long elapsed;
	
	private int patrolCount=0; 

	private int distance =0;
	
	private GameObject player=null;
	
	float playerX,playerY;

	Texture tex = Game.getInstance();
	
	public ShootingBandit(float x,float y, ObjectId id)
	{	
		
		super(x,y,id,EnemyId.ShootingBandit);
		
		setWidth (32);
		setHeight(48);
		
		setHp(200);
		
		shootingRight= new Animation(3,tex.shoot[0],tex.shoot[1],tex.shoot[2],tex.shoot[3],tex.shoot[4],tex.shoot[5],tex.shoot[6],tex.shoot[7]);
		shootingLeft= new Animation(3,tex.shoot[8],tex.shoot[9],tex.shoot[10],tex.shoot[11],tex.shoot[12],tex.shoot[13],tex.shoot[14],tex.shoot[15]);

		slashingRight= new Animation(3,tex.banditStab[0],tex.banditStab[1],tex.banditStab[2],tex.banditStab[3],tex.banditStab[4]);
		slashingLeft= new Animation(3,tex.banditStab[5],tex.banditStab[6],tex.banditStab[7],tex.banditStab[8],tex.banditStab[9]);
		
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
		if(slashing)
		{
			swordCollision(object);
		
		}
		
		
		
		
		

		shootingRight.runAnimation();
		slashingRight.runAnimation();		
		
		
		
		shootingLeft.runAnimation();
		slashingLeft.runAnimation();
		
		
		
		if(shootingRight.getCount()==7||shootingLeft.getCount()==7) 
		{
			
			
			
			shooting = false;
			left=eleft;
			right=eright;
			KeyInput.response=true; 
			resetIndex();
		
		}else
		if(slashingRight.getCount()==5||slashingLeft.getCount()==5) 
		{
			slashing = false;
			resetIndex();
			KeyInput.response=true; 
			
		
			
		
			
			
			
		}

		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		
		if(shooting&&velY==0&&!slashing)
		{	
			if(shootingRight.getCount()==4||shootingLeft.getCount()==4)
			{	
					long elapsed = (System.nanoTime()- firingTimer)/1000000;
					if(elapsed>firingDelay)
				{try{
					
					if(KeyInput.weaponIndex==0)
						Game.handler.addObject(new BulletType1(x,y,ObjectId.Bullets,facingRight));
					else
					if(KeyInput.weaponIndex==2)
						Game.handler.addObject(new BulletType2(x,y,ObjectId.Bullets,facingRight));
					
					
					
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
			if(diff>200) { Patrolling(); slashing=false; shooting=false; }
			else if(diff>100 || diffY>48) {left=true; right=false; facingRight=false; slashing=false; shooting=false;} //move towards player
			else if(diff>30) { velX=0; left=false; right=false; facingRight=false; shooting=true; slashing=false;} //shoot player
			else   { velX=0; left=false; right=false; facingRight=false; slashing=true; shooting=false;} //shoot player
			}
		else		//player is to left
			{ 
			int diff=(int)(playerX-x);
			if(diff>200) { Patrolling(); slashing=false; shooting=false; }
			else if(diff>100 || diffY>48) {left=false; right=true; facingRight=true; slashing=false; shooting=false;} //move towards player
			else if(diff>30) { velX=0; left=false; right=false; facingRight=true; shooting=true; slashing=false;} //shoot player
			else   { velX=0; left=false; right=false; facingRight=true; slashing=true; shooting=false;} //shoot player
			}
		
	}
	
	
	private  void bulletCollision(LinkedList<GameObject> object)
	{
		for(int i=0;i<object.size();i++)
		{
			GameObject tempBullet = object.get(i);
			
			if(tempBullet.getId()==ObjectId.Bullets&&tempBullet.getBulletId()!=BulletId.Arrow)
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
		
				slashingLeft.drawAnimation(g,(int)(x-width/2)-16,(int)y);
			}
			velX=0;
			velY=0;
			
			
		}else
		
		
		if(shooting&&velY==0)
		{
			
			if(facingRight)
			{
				
				shootingRight.drawAnimation(g,(int)x,(int)y);
				
				
			
			}
			else
			{
		
				shootingLeft.drawAnimation(g,(int)(x-width/2),(int)y);
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
		
	}
	

	
	

	public Rectangle getswordBounds()
	{			if(facingRight)
	
			return new Rectangle((int)(x+width-10),(int)(y+10),25,10) ;	//swordRight
		
		else
		
			return new Rectangle((int)(x-15),(int)(y+10),25,10) ;	//swordLeft
	
	
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