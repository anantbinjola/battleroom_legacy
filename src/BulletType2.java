package com.payne.game.objects;

import java.util.LinkedList;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.Rectangle;

import com.payne.game.framework.GameObject;

import com.payne.game.framework.ObjectId;

import com.payne.game.framework.BulletId;

import com.payne.game.framework.Texture;

import com.payne.game.window.Handler;

import com.payne.game.window.Game;

import com.payne.game.window.Animation;


public class BulletType2 extends Bullet
{  
	private ObjectId id;
	
	Texture tex = Game.getInstance();
	
	private float dX=8.0f;
	
	private int index =0;
	
	private Animation bulletRight,bulletLeft;
	

	public void resetIndex()
	{
		index=0;
	}
	
	public boolean collisionCheck;	
	
	public BulletType2(float x,float y,ObjectId id, boolean facingRight)
	{	
		super(x,y,id,facingRight,BulletId.Bullet2);
		
		if(facingRight)
		{	
			this.x=x+39; 
			velX=14;
		
		
		}
		else 
		{
			this.x=x-19;
			velX=-14;
		}

		this.y=y+6;
		bulletRight= new Animation(3,tex.bullet2[0],tex.bullet2[1]);
		bulletLeft= new Animation(3,tex.bullet2[2],tex.bullet2[3]);
	
	}

	public void tick(LinkedList<GameObject> object)
	{
			if(facingRight)
		{
			bulletRight.runAnimation();
		
		}
		else
		{				
			bulletLeft.runAnimation();
		
		}
		
		if(index<5)	
			index++;
		else 
			x=x+velX;

	}
	
	public  void render(Graphics2D g)
	{
		
		if(facingRight)
		{
			bulletRight.drawAnimation(g,(int)x,(int)y);		
		}
		else		
		{
			bulletLeft.drawAnimation(g,(int)x,(int)y);
		}	

	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)(x),(int)y,16,8);
	}

}