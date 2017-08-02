package com.payne.game.objects;

import java.util.LinkedList;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.Rectangle;

import com.payne.game.framework.GameObject;

import com.payne.game.framework.ObjectId;

import com.payne.game.framework.Texture;

import com.payne.game.framework.BulletId;

import com.payne.game.window.Handler;

import com.payne.game.window.Game;

import com.payne.game.window.Animation;


public abstract class Bullet extends GameObject
{  
	
	Texture tex = Game.getInstance();
	
	protected boolean facingRight=true;

	public boolean collisionCheck;	
	
	public Bullet(float x,float y,ObjectId id, boolean facingRight,BulletId bulletId)
	{	
		super(x,y,id);
		this.facingRight=facingRight;
		
		this.bulletId=bulletId;
	
	}

	public Rectangle getBoundsLeft()
	{
		
		return new Rectangle((int)x,(int)y,2,2);
		
	}
	
	public Rectangle getBoundsTop()
	{
		
		return new Rectangle((int)x,(int)y,2,2);
		
	}
	
	public Rectangle getBoundsRight()
	{
		
		return new Rectangle((int)x,(int)y,2,2);
		
	}
	
	
	public Rectangle getBoundsBottom()
	{
		
		return new Rectangle((int)x,(int)y,2,2);
		
	}

}