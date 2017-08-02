package com.payne.game.window;

import com.payne.game.framework.GameObject;
import com.payne.game.framework.ObjectId;
import com.payne.game.framework.BulletId;

import com.payne.game.objects.Block;



import java.util.LinkedList;

import java.awt.Graphics2D;

import java.awt.Color;

import com.payne.game.window.Game;






public class Handler
{
	
	public int noE =0,enemy = 0;
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	
	private Game game = new Game();
	
	public void tick()
	{
	
		for(int i=0; i < object.size();i++)
		{
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	
	}
	
	
	
	public void render(Graphics2D g)
	{
		for(int i=0; i<object.size();i++)
		{
			
			tempObject = object.get(i);
			
			
			if(tempObject.getId()!=ObjectId.Block && tempObject.getId() != ObjectId.Player)
			{	
				
				tempObject.render(g);
			
			}
			
			
			
		}
		
		for(int i=0; i<object.size();i++)
		{
			
			tempObject = object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{	
				tempObject.render(g);
				break;
			}
			
		}
	
	
	}
	
	public void addObject(GameObject Object)
	{
		if(Object.getId()==ObjectId.Enemy )
		{
			noE++;
		}
	
		
		this.object.add(Object);
		
	}
	public void removeObject(GameObject Object, int i)
	{
		if(Object.getId() == ObjectId.Enemy)
		{
			noE --;
		}
		
		this.object.remove(i);
		
	}
	
	public void clear()
	{
		noE = 0;
		object.clear();
	
	}

	
	
	
	
	
	
	
	
	
	
}