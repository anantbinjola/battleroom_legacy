package com.payne.game.objects;

import java.util.LinkedList;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.Rectangle;

import com.payne.game.framework.GameObject;

import com.payne.game.framework.ObjectId;

import com.payne.game.framework.Texture;

import com.payne.game.window.Game;


public class Block extends GameObject
{  

	public void resetIndex(){}
	
	private int type;
	
	private ObjectId id;
	
		Texture tex = Game.getInstance();

	
	
	public Block(float x,float y, int type,ObjectId id)
	{	
		
		super(x,y,id);
		
		this.type=type;
		
	}
	
	public void tick(LinkedList<GameObject> object)
	{
	
	
	}
	
	public  void render(Graphics2D g)
	{
		if(type==0)
		{
			g.drawImage(tex.block[0],(int)x,(int)y,null);
		}
		
		if(type==1)
		{
			g.drawImage(tex.block[1],(int)x,(int)y,null);
		}
		
	
		
	
	}
	
	public Rectangle getBounds()
	{
		
		return new Rectangle((int)x,(int)y,32,32);
		
	}
	
	public Rectangle getBoundsLeft()
	{
		
		return new Rectangle((int)x,(int)y,32,32);
		
	}
	
	public Rectangle getBoundsTop()
	{
		
		return new Rectangle((int)x,(int)y,32,32);
		
	}
	
	public Rectangle getBoundsRight()
	{
		
		return new Rectangle((int)x,(int)y,32,32);
		
	}
	
	
	public Rectangle getBoundsBottom()
	{
		
		return new Rectangle((int)x,(int)y,32,32);
		
	}
	
	
	


}