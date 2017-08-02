package com.payne.game.objects;

import java.util.LinkedList;

import java.util.ArrayList;

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

public class Player  extends GameObject
{  
	private float width = 32, height = 48;
	
	private float gravity = 0.5f, dX = 0.25f;
	
	private int distance =0,bulletNo=0;
	
	private int cnt;
	
	Game game = new Game();
	
	private float MAX_SPEED = 4f;
	
	private final float MAX_FALLSPEED = 10f;
	
	private boolean dropSlashEnd = false;

	
	private boolean remove=false,collision=false,oneHit = false, comboStart = false;
	
	private BulletType1 tempBullet;
	
	public Animation 
		playerWalk,playerWalkLeft,
		shootingLeft,shootingRight, 
		comboSlash2Left,comboSlash2Right,
		comboSlash3Right,comboSlash3Left,
		comboSlash1Left,comboSlash1Right,
		upperSlashRight, upperSlashLeft,
		downSlashRight,downSlashLeft,
		
		dropSlashRightStart,dropSlashLeftStart,
		upSlashRightStart,upSlashLeftStart,
		
		dropSlashRightEnd,dropSlashLeftEnd,
		upSlashRightEnd,upSlashLeftEnd,
		
		
		
		aerialSlashRight,aerialSlashLeft;

	private boolean facingRight = true,shoot=false,slash=false;
	
	private long firingTimer,firingDelay,comboTime = 0, comboWait = 0;
	
	
	public void resetIndex()
	{
		shootingRight.resetIndex();
		
		shootingLeft.resetIndex();
		
		comboSlash1Right.resetIndex();
		
		comboSlash2Right.resetIndex();
		
		comboSlash3Right.resetIndex();
		
		
		comboSlash1Left.resetIndex();
		
		comboSlash2Left.resetIndex();
		
		comboSlash3Left.resetIndex();
		
		
		upperSlashRight.resetIndex();
		
		upperSlashLeft.resetIndex();
		
		
		downSlashRight.resetIndex();
		
		downSlashLeft.resetIndex();
		
		
		
		
		
		
		
		
		aerialSlashRight.resetIndex();
		
		aerialSlashLeft.resetIndex();
		
		
		bulletNo = 0;
		
		oneHit = false;
	
	}
	
	public void resetupdownSlashIndex()
	{
		dropSlashRightStart.resetIndex();
		dropSlashLeftStart.resetIndex();
		
		upSlashRightStart.resetIndex();
		upSlashLeftStart.resetIndex();
		
		
		
		
		
		
		dropSlashRightEnd.resetIndex();
		dropSlashLeftEnd.resetIndex();
		
		upSlashRightEnd.resetIndex();
		upSlashLeftEnd.resetIndex();
	}
	
	

	
	Texture tex = Game.getInstance();
	
	
	public Player(float x,float y, ObjectId id)
	{	
		
		super(x,y,id);
		

		shootingRight= new Animation(3,tex.shoot[0],tex.shoot[1],tex.shoot[2],tex.shoot[3],tex.shoot[4],tex.shoot[5],tex.shoot[6],tex.shoot[7],tex.shoot[7]);
		shootingLeft= new Animation(3,tex.shoot[8],tex.shoot[9],tex.shoot[10],tex.shoot[11],tex.shoot[12],tex.shoot[13],tex.shoot[14],tex.shoot[15],tex.shoot[15]);
		
	
		
		comboSlash1Right= new Animation(1,tex.slashCombo1[0],tex.slashCombo1[1],tex.slashCombo1[2],tex.slashCombo1[3],tex.slashCombo1[4],tex.slashCombo1[5],tex.slashCombo1[6],tex.slashCombo1[7],tex.slashCombo1[7]);
		
		comboSlash2Right= new Animation(1,tex.slashCombo2[0],tex.slashCombo2[1],tex.slashCombo2[2],tex.slashCombo2[3],tex.slashCombo2[4],tex.slashCombo2[5],tex.slashCombo2[6],tex.slashCombo2[7],tex.slashCombo2[7]);
		

		comboSlash3Right= new Animation(1,tex.slashCombo3[0],tex.slashCombo3[1],tex.slashCombo3[2],tex.slashCombo3[3],tex.slashCombo3[4],tex.slashCombo3[5],tex.slashCombo3[6],tex.slashCombo3[7],tex.slashCombo3[7]);		
	
		aerialSlashRight= new Animation(1,tex.aerialSlash[0],tex.aerialSlash[1],tex.aerialSlash[2],tex.aerialSlash[3],tex.aerialSlash[4],tex.aerialSlash[5],tex.aerialSlash[6],tex.aerialSlash[7],tex.aerialSlash[7]);
	
		
		upperSlashRight= new Animation(1,tex.upperSlash[0],tex.upperSlash[1],tex.upperSlash[2],tex.upperSlash[3],tex.upperSlash[4],tex.upperSlash[5],tex.upperSlash[6],tex.upperSlash[7],tex.upperSlash[8],tex.upperSlash[8]);		
		
		downSlashRight= new Animation(1,tex.downSlash[0],tex.downSlash[1],tex.downSlash[2],tex.downSlash[3],tex.downSlash[4],tex.downSlash[5],tex.downSlash[6],tex.downSlash[7],tex.downSlash[8],tex.downSlash[8]);		
		
		
		dropSlashRightStart= new Animation(3,tex.dropSlash[0],tex.dropSlash[1],tex.dropSlash[2],tex.dropSlash[3],tex.dropSlash[8],tex.dropSlash[8],tex.dropSlash[8],tex.dropSlash[8]);		
		
		
		
		dropSlashRightEnd= new Animation(3,tex.dropSlash[10],tex.dropSlash[11],tex.dropSlash[12],tex.dropSlash[13],tex.dropSlash[13]);		
		
		
		
		
		comboSlash1Left= new Animation(1,tex.slashCombo1[8],tex.slashCombo1[9],tex.slashCombo1[10],tex.slashCombo1[11],tex.slashCombo1[12],tex.slashCombo1[13],tex.slashCombo1[14],tex.slashCombo1[15],tex.slashCombo1[15]);
		
		comboSlash2Left= new Animation(1,tex.slashCombo2[8],tex.slashCombo2[9],tex.slashCombo2[10],tex.slashCombo2[11],tex.slashCombo2[12],tex.slashCombo2[13],tex.slashCombo2[14],tex.slashCombo2[15],tex.slashCombo2[15]);
		
		comboSlash3Left= new Animation(1,tex.slashCombo3[8],tex.slashCombo3[9],tex.slashCombo3[10],tex.slashCombo3[11],tex.slashCombo3[12],tex.slashCombo3[13],tex.slashCombo3[14],tex.slashCombo3[15],tex.slashCombo3[15]);
		
		aerialSlashLeft= new Animation(1,tex.aerialSlash[8],tex.aerialSlash[9],tex.aerialSlash[10],tex.aerialSlash[11],tex.aerialSlash[12],tex.aerialSlash[13],tex.aerialSlash[14],tex.aerialSlash[15],tex.aerialSlash[15]);
		
		
		upperSlashLeft= new Animation(1,tex.upperSlash[9],tex.upperSlash[10],tex.upperSlash[11],tex.upperSlash[12],tex.upperSlash[13],tex.upperSlash[14],tex.upperSlash[15],tex.upperSlash[16],tex.upperSlash[17],tex.upperSlash[17]);		
		
		downSlashLeft= new Animation(1,tex.downSlash[9],tex.downSlash[10],tex.downSlash[11],tex.downSlash[12],tex.downSlash[13],tex.downSlash[14],tex.downSlash[15],tex.downSlash[16],tex.downSlash[17],tex.downSlash[17]);		
		
		
		dropSlashLeftStart= new Animation(3,tex.dropSlash[4],tex.dropSlash[5],tex.dropSlash[6],tex.dropSlash[7],tex.dropSlash[9]);		
		
		
		
		dropSlashLeftEnd= new Animation(3,tex.dropSlash[14],tex.dropSlash[15],tex.dropSlash[16],tex.dropSlash[17],tex.dropSlash[17]);		
		
		hp=500;
		
		
		
		
		
		playerWalk = new Animation(6,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6]);
		playerWalkLeft = new Animation(6,tex.player[9],tex.player[10],tex.player[11],tex.player[12],tex.player[13],tex.player[14]);
		
		firingTimer=System.nanoTime();
		
		firingDelay = 500;
		
		comboTime = 50;
		
		
		
	}
	
	public void tick(LinkedList<GameObject> object)
	{	
	
		// if(velY > 0)
		// {
			// MAX_SPEED = 1f;
		// }else
		// {
			// MAX_SPEED = 4f;
		// }
		
		if(velY==0)
		{
			upperSlashing = false;
			
		}
		
		
		
		
		if(isCrouching())
		{
			height=32;
			y=y+8;
			
		}else {	height = 48; }
		if(right)
		{	if(!isCrouching())
			{	
				
				if(velX>=MAX_SPEED)
					velX=MAX_SPEED;
				else
				if(shooting)
					velX=0;
				else
					velX = velX+dX;
			
			}
		}
		if(left)
		{	if(!isCrouching())
			{	
				
				if(velX<=-MAX_SPEED)
					velX =-MAX_SPEED;
				else
				if(shooting)
					velX=0;
				else
				velX= velX-dX;
			}
		}
		
		if(!isCrouching())
		{
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
		
	
		
		
		if(shoot)
		{
			bulletCollision(object);
			
		}
		if(slashing)
		{
			swordCollision(object);
		
		}
		
		if(damage)
		{
			
			if(damageRight)
			{
				x=x-6*(float)Math.cos(45);
				y=y-6*(float)Math.sin(45);
				
			}
			else
			{	
				x=x+6*(float)Math.cos(45);
				y=y-6*(float)Math.sin(45);
				
			}
			
			
			cnt++;
			
			if(cnt==10)
			{
				damage = false;
				cnt=0;
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		aerialSlashRight.runAnimation();
		

		shootingRight.runAnimation();
		
		if(facingRight)
		{	
			
			
			
			if(comboNumber == 1)
			{
				comboSlash1Right.runAnimation();
			}else
			if(comboNumber == 2)
			{
				comboSlash2Right.runAnimation();
			}else
			if(comboNumber == 3)
			{
				comboSlash3Right.runAnimation();
			}
			
		
		}else
		if(!facingRight)
		{	
			if(jumping)
			{
				aerialSlashLeft.runAnimation();
			}else
			if(comboNumber == 1)
			{
				comboSlash1Left.runAnimation();
			}else
			if(comboNumber == 2)
			{
				comboSlash2Left.runAnimation();
			}else
			if(comboNumber == 3)
			{
				comboSlash3Left.runAnimation();
			}
		
		}
		
		if(upperSlashing||downSlashing)
		{
			velX = 0;
		}
		
		
		shootingLeft.runAnimation();
		
		if(dropSlashEnd)
		{
			if(dropSlashRightEnd.getCount() == 4 || dropSlashLeftEnd.getCount() == 4)
			{
				
				dropSlashRightStart.resetIndex();
				
				dropSlashRightEnd.resetIndex();
				
				dropSlashRightStart.setDelay(3);
				
				downSlashing = false;
				
				slashing = false;
				
				dropSlashEnd = false;
				
				//resetupdownSlashIndex();
				
				left=eleft;
				
				right=eright;
				
				KeyInput.response=true; 
				
			}
		}
		else
		{
			if(downSlashing)
			{
				if(dropSlashRightStart.getCount() == 5 || dropSlashLeftStart.getCount() == 5)
				{
					
					dropSlashRightStart.setDelay(0);
					
					
					
					
					
					
					
					
					
					
					left=eleft;
					
					right=eright;
					
					KeyInput.response=true; 
					
				}
			}
		}
		
		
		
		
		
		if(shooting)
		{
			if(shootingRight.getCount()==8||shootingLeft.getCount()==8 &&!slashing) 
			{
				
				
				
				shooting = false;
				left=eleft;
				right=eright;
				KeyInput.response=true; 
				resetIndex();
			
		
			}
		}	
		
		if(slashing)
		{
			comboWait = 0;
			
			
			
			if(aerialSlashRight.getCount() == 8 || aerialSlashLeft.getCount() == 8)
			{
				
				aerialSlashing = false;
				slashing = false;

				left=eleft;
				right=eright;
				
				KeyInput.response=true; 
				
			}
			else
			if(upperSlashRight.getCount() == 8 || upperSlashLeft.getCount() == 8)
			{
				upperSlashRight.setDelay(0);
				upperSlashLeft.setDelay(0);
				slashing = false;

				left=eleft;
				right=eright;
				
				KeyInput.response=true; 
				
			}else
			{
				upperSlashRight.setDelay(1);
				upperSlashLeft.setDelay(1);
			}
			/* if(downSlashRight.getCount() == 8 || downSlashLeft.getCount() == 8)
			{
				downSlashRight.setDelay(0);
				downSlashLeft.setDelay(0);
				slashing = false;

				left=eleft;
				right=eright;
				
				KeyInput.response=true; 
				
			}else
			{
				downSlashRight.setDelay(1);
				downSlashLeft.setDelay(1);
			} */
			
			
			if(velY!=0 || aerialSlashing)
			{
				comboNumber = 1;
			}
			else
			if(comboNumber == 1 )
			{
				
				if(comboSlash1Right.getCount()==8||comboSlash1Left.getCount()==8) 
				{
					
					
					slashing = false;
					
					left=eleft;
					right=eright;
				
					KeyInput.response=true; 
					
					
					
					//comboSlash2Right.setDelay(0);
					
					
					
					comboNumber= 2;
					
					comboStart = true;
					comboWait = 0;
					
				}
				
			}
			else
			if(comboNumber == 2 )
			{
				if(comboSlash2Right.getCount()==8||comboSlash2Left.getCount()==8) 
				{
					
					
					
					slashing = false;
					
					left=eleft;
					right=eright;
					
					KeyInput.response=true; 
					
					
					
					//comboSlash2Right.setDelay(0);
					
					comboNumber= 3;
					comboWait = 0;
					
				
				}
			
			}		
			else			
			if(comboNumber == 3 )
			{
				if(comboSlash3Right.getCount()==8||comboSlash3Left.getCount()==8) 
				{
					
					
					
					slashing = false;
					
					left=eleft;
					right=eright;
					
					KeyInput.response=true; 
					
					
					
					//comboSlash2Right.setDelay(0);
					
					comboNumber= 1;
					comboWait = 0;
					
				
				}
			
			}		
			
		}

		if(comboStart)
		{
			comboWait++;
		}else
		{
			comboWait = 0;
		}

		if(comboWait > comboTime && comboStart)
		{
			
			comboNumber = 1;
			
			comboWait = 0;
			
			comboStart = false;
		}


		
	
		
		
		
		
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		
		if(shooting&&velY==0&&!jumping&&!crouching&&!slashing)
		{	
			if(shootingRight.getCount()==4||shootingLeft.getCount()==4)
			{	
				long elapsed = (System.nanoTime()- firingTimer)/1000000;
				if(elapsed>firingDelay)
				{
					try
					{
						
						if(KeyInput.weaponIndex==0&&bulletNo ==0)
						{
							Game.handler.addObject(new BulletType1(x,y,ObjectId.Bullets,facingRight));
							bulletNo++;
						}
						else
						if(KeyInput.weaponIndex==2&&bulletNo ==0)
						{
							Game.handler.addObject(new BulletType2(x,y,ObjectId.Bullets,facingRight));
							bulletNo++;
						}
						else
						if(KeyInput.weaponIndex==3)
							//Game.handler.addObject(new Arrow(x,y,ObjectId.Bullets,facingRight,10,0));
						
						
						firingTimer = System.nanoTime();
			
					}catch(Exception e)
					{
				
					}
					shoot=true;
				}
				
			}
		}
		if(facingRight)
		{
			if(upperSlashing)
			{
				upperSlashRight.runAnimation();
			}else
			if(downSlashing)
			{
	
				downSlashRight.runAnimation();
				
				
				
				
				if(dropSlashEnd)
				{
					dropSlashRightEnd.runAnimation();
				}
				else
				{
					dropSlashRightStart.runAnimation();
				}
				
				
				
			}
		}
		else
		if(!facingRight)
		{
			if(upperSlashing)
			{
				upperSlashLeft.runAnimation();
			}else
			if(downSlashing)
			{
				downSlashLeft.runAnimation();
			
				if(dropSlashEnd)
				{
					dropSlashLeftEnd.runAnimation();
				}
				else
				{
					dropSlashLeftStart.runAnimation();
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
			
		}
		else
		if(upperSlashing)
		{
			if(facingRight)
			{
			
				upperSlashRight.drawAnimation(g,(int)x,(int)y-16);
				
			}
			else
			if(!facingRight)
			{
			
				upperSlashLeft.drawAnimation(g,(int)(x-width/2)-16,(int)y-16);
				
			}
		}
		else
		if(downSlashing)
		{
			
		
			if(facingRight)
			{
				if(dropSlashEnd)
				{
					dropSlashRightEnd.drawAnimation(g,(int)x,(int)y -16);
					
				}else
				{
					dropSlashRightStart.drawAnimation(g,(int)x,(int)y -16);
				}
				
				
				
				
			
			}
			else
			if(!facingRight)
			{
				
				
				if(dropSlashEnd)
				{
					dropSlashRightEnd.drawAnimation(g,(int)x,(int)y -16);
				}else
				{
					dropSlashRightStart.drawAnimation(g,(int)x,(int)y -16);
					
				}
				
				
			}
		}
		else
		if(aerialSlashing)
		{
			if(facingRight)
			{
			
				aerialSlashRight.drawAnimation(g,(int)x,(int)y-16);
				
			}
			else
			if(!facingRight)
			{
				aerialSlashLeft.drawAnimation(g,(int)(x - width/2)-16,(int)y-16);
			}
			
		}else
		if(slashing&&velY==0&&!crouching&&!shooting)
		{	
			if(facingRight)
			{
				
				if(!jumping)
				{
					if(comboNumber == 1)
					{
						comboSlash1Right.drawAnimation(g,(int)x,(int)y-16);
					}
					else
					if(comboNumber == 2)
					{
						comboSlash2Right.drawAnimation(g,(int)(x),(int)y-16);
					}
					else if(comboNumber == 3)
					{
						comboSlash3Right.drawAnimation(g,(int)(x),(int)y-16);
					}
				}
				
				
				
			}
			else
			{
				if(!jumping)
				{
					if(comboNumber == 1)
					{
						comboSlash1Left.drawAnimation(g,(int)(x-width/2)-16,(int)y-16);
					
					}
					else
					if(comboNumber == 2)
					{
						comboSlash2Left.drawAnimation(g,(int)(x - width/2)-16,(int)y-16);
					
					}
					else if(comboNumber == 3)
					{
						comboSlash3Left.drawAnimation(g,(int)(x - width/2)-16,(int)y-16);
					}
				}
				
				
				
			}
			velX=0;
			velY=0;
			
			
		}else
		if(shooting&&velY==0&&!jumping&&!crouching&&!slashing)
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
		{	
			falling=true;
			
			if(facingRight)
			{
				
				g.drawImage(tex.playerJump[0],(int)x,(int)y,null);
			
			}
			else 
			if(!facingRight)
			{
				g.drawImage(tex.playerJump[1],(int)x,(int)y,null);
			}
				
		}
		else 	
		if(velY>0)
		{	
		if(facingRight)
			g.drawImage(tex.fall[0],(int)x,(int)y,null);
		
		else 
			g.drawImage(tex.fall[1],(int)x,(int)y,null);
		
		
		}
		else
	
	
			if(velX!=0 )
		{	
			if(facingRight)
			{	
				
				playerWalk.drawAnimation(g,(int)x,(int)y);
				
			}
			else 
			{
				
				playerWalkLeft.drawAnimation(g,(int)x,(int)y);
		
			}
			
			
		}else
			
		{
			if(facingRight)
			{ 
				
				g.drawImage(tex.player[0],(int)x,(int)y,null);
				
				
			}
			else if(!facingRight)
			{
				g.drawImage(tex.player[15],(int)x,(int)y,null);
				
				
				
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
		
		
		/*
		if(slashing&&facingRight&&velX==0&&velY==0)
		{
			
				g.drawRect((int)(x+width-10),(int)(y+10),25,10) ;					//sword
		
		}else if(slashing&&!facingRight&&velX==0&&velY==0)
		{
			
				g.drawRect((int)(x-15),(int)(y+10),25,10) ;							//sword
			
		}
		
		*/
		
		
		
		}
		
		
		/*g.drawRect((int)((int)x + (width/2)-((width/2)/2)),(int)y,(int)(int)width/2,(int)height/2) ;	//top
		g.drawRect((int)(x-((velX>0)?velX:-velX)),(int)y+2,(int)(6+((velX>0)?velX:-velX)),(int)height-5);	//left
		g.drawRect((int)((int)x + (width/2)-((width/2)/2)) ,(int)((int)y + height/2),(int)(int)width/2,(int)height/2); 	//bottom
		g.drawRect((int)((int)x + width - 5),(int)y+2,(int)(5+((velX>0)?velX:-velX)),(int)height-5);//right
		
		
		g.drawString("x = " + x + " y = " + y,(int)x,(int)y);
		*/
		
		
		
		g.drawString("slashing = "+slashing+" response is "+KeyInput.response , (int)x,(int)y);
		
		/*
		
		g.drawString(" upperSlashing is "+upperSlashing	 , (int)x,(int)300-20);
		
		
		g.drawString(" right DropSlash Count End is "+dropSlashRightEnd.getCount()	 , (int)x-100,(int)500-60);
		
		g.drawString(" right DropSlash Start Count is "+dropSlashRightStart.getCount()	 , (int)x-100,(int)500-70);
		
		
		g.drawString(" downSlashing is "+downSlashing	 , (int)x,(int)300-28);
		
		g.drawString(" velY is "+aerialSlashing	 , (int)x,(int)300-40);
		
		g.drawString(" DropSlashEnd is "+dropSlashEnd, (int)x,(int)300-50);
		
		
		*/
		

		
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
							Game.handler.removeObject(tempBullet,i);
							// object.remove(i);
							i--;
							return;
						}
					}else
				
					if(tempObject.getId()==ObjectId.Enemy)
					{
						if(tempBullet.getBounds().intersects(tempObject.getBounds()))
						{
							int tempHp=tempObject.getHp();
							Game.handler.removeObject(tempBullet,i);
							
							// object.remove(i);
						
							i--;
							
							
							
							if(tempBullet.getBulletId()==BulletId.Bullet1)
							{	
								tempHp=tempObject.getHp()-100;
							}
							else 
							if(tempBullet.getBulletId()==BulletId.Bullet2)
							{	
								tempHp=tempObject.getHp()-200;
							}
							
							
							tempObject.setHp(tempHp);
							if(tempHp<=0)
							{
								Game.handler.removeObject(tempObject,i);
								
								// object.remove(j);
								j--;
							
								//Game.enemies--;
								
							}
							return;
							
						}
					
					}
					
				}
			
			}
	
		}
	
	}
	
	
	private  void swordCollision(LinkedList<GameObject> object)
	{
		
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId()==ObjectId.Enemy)
			{
				if(getswordBounds().intersects(tempObject.getBounds())&&!oneHit)
				{	
					int tempHp = 0;
					if(upperSlashing)
					{
						tempObject.setVelY(-16);
						
						tempHp = tempObject.getHp() - 500;
						
					}
					else
					if(downSlashing)
					{
						tempObject.setVelY(30);
						
						tempHp = tempObject.getHp() - 500;
					}else
					if(comboNumber == 1)
					{
						tempHp=tempObject.getHp()-50;
					}
					else
					if(comboNumber == 2	)
					{
						tempHp=tempObject.getHp()-100;
					}
					else
					if(comboNumber == 3)
					{
						tempHp = tempObject.getHp() - 200;
					}
					
					
					tempObject.setHp(tempHp);
					if(tempHp<=0)
					{
						Game.handler.removeObject(tempObject,i);
						
						// object.remove(i);
						i--;
						// Game.enemies--;
					}
					
					oneHit = true;
					
				}
				
			}
			
	
	
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private  void Collision(LinkedList<GameObject> object)
	{
		
		
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Block)
			{
			
				
				
				//UP
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{	y=tempObject.getY() + 32;
					velY=0;
					
					upperSlashing = false;
					
					damage = false;
					KeyInput.response = true;

					
				}
				
			
				//DOWN
				if(getBoundsBottom().intersects(tempObject.getBounds()))
				{	falling=false;
					y=tempObject.getY() - height+1;
					velY=0;
					
					
					
					if(downSlashing)
					{
						
						
						
						dropSlashEnd = true;
					
						
						
					}
					
					
					jumping=false;
					
					
					
				}
				else
				{
					falling = true;
				}
				
				
				//RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{	x=tempObject.getX() - width+1;
					velX=0;
					damage = false;
					
				}
				
				//left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{	x=tempObject.getX() + width + 0.5f;
					velX=0;
					damage = false;
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
		return new Rectangle((int)(x-((velX>0)?velX:-velX)),(int)y+2,(int)(6+((velX>0)?velX:-velX)),(int)height-5);
	}
	
	
	
	

	






	public Rectangle getswordBounds()
	{	
	
	
	
		
		if(facingRight)
	
			return new Rectangle((int)(x+width-10),(int)(y+10),25,10) ;	//swordRight
		
		else
		
			return new Rectangle((int)(x-15),(int)(y+10),25,10) ;	//swordLeft
		
		
	}
		

	


}