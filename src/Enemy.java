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

import com.payne.game.framework.EnemyId;

public abstract class Enemy extends GameObject
{  
	protected float width , height;
	
	protected float gravity = 0.5f, dX = 0.3f;
	
	protected int cnt;
	
	protected final float MAX_SPEED = 0.5f;
	
	protected final float MAX_FALLSPEED = 20f;
	
	protected EnemyId enemyId;
	
	protected Animation playerWalk, playerWalkLeft,shootingLeft,shootingRight, slashingLeft,slashingRight;

	protected boolean facingRight = true,shoot=false,slash=false;
	
	protected long firingTimer,firingDelay;
	
	protected int maxPatrolCount=500;//no of pixels in x dir to patrol
	
	public void resetIndex()
	{
	 shootingRight.resetIndex();
	 shootingLeft.resetIndex();
	 slashingRight.resetIndex();
	 slashingLeft.resetIndex();
	 
	
	}
	
	Texture tex = Game.getInstance();
	
	public Enemy(float x,float y, ObjectId id,EnemyId enemyId)
	{	
		super(x,y,id);
		
		this.enemyId=enemyId;
		
	}
	
	public void setHeight(float height)
	{
		this.height = height;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}

	public abstract void tick(LinkedList<GameObject> object);
	
	

	
	public abstract void Collision(LinkedList<GameObject> object);
	
	public abstract Rectangle getBounds();
	
	public abstract void enemyAI();
	


}