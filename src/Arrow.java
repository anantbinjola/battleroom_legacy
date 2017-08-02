package com.payne.game.objects;

import java.util.LinkedList;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.geom.AffineTransform;

import java.awt.Rectangle;

import com.payne.game.framework.GameObject;

import com.payne.game.framework.ObjectId;

import com.payne.game.framework.BulletId;

import com.payne.game.framework.Texture;

import com.payne.game.window.Handler;

import com.payne.game.window.Game;

import com.payne.game.window.Animation;

import java.awt.image.BufferedImage;


public class Arrow extends Bullet
{  
	private ObjectId id;
	
	private Graphics2D g;
	
	private int direction = 0;
	
	Texture tex = Game.getInstance();
	
	
	private float u = 21;
	private int index =0;
	
	private float gravity = 0.5f;
	
	private int MAXSPEED = 3;
	
	private long startTime ;
	
	private long endTime ;
	
	private long elapsed;
	
	private long time;
	
	private float range = 0;
	
	private double theta = 0.0;
	
	private Game game = new Game();

	public void resetIndex()
	{
		index=0;
	}
	

	
	public boolean collisionCheck;	
	
	public Arrow(float x,float y,ObjectId id, boolean facingRight,float u,int direction)
	{	
		super(x,y,id,facingRight,BulletId.Arrow);
		
		this.u = u;
		this.direction = direction;
		
		
		velX = u*(float)Math.cos(Math.toRadians(direction));
		
		velY=-u*(float)Math.sin(Math.toRadians(direction));
		
		
		if(!facingRight) velX=-velX;
		
		
	}

	public void tick(LinkedList<GameObject> object)
	{
		
		
		velY+=gravity;	
		x=x+velX;	
		y=y+velY;
		
		
	
	}
	public  void render(Graphics2D g)
	{
		
		
		
		float theta=(float)Math.atan(velY/velX);
		
		g.rotate(theta,(int)x,(int)y);
		
		if(facingRight)
		{
			g.drawImage(tex.arrow[0],(int)x-12,(int)y-1,null);			
		}
		
		else
		{	
			g.drawImage(tex.arrow[1],(int)x-12,(int)y-1,null);			
		
		}
		
		
		
		g.drawRect((int)(x),(int)y,4,4);
		
		g.rotate(-theta,(int)x,(int)y);
	
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)(x),(int)y,2,2);
	}

	
	
	
}
