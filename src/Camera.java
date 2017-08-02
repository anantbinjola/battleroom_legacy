package com.payne.game.window;

import com.payne.game.framework.GameObject;

/**
	Class used to make the screen move as the player moves in the game
	
	Variable x,y					:	Position of camera
*/

public class Camera
{
	private float x,y;
	
	//Constructor to set the x,y coordinates of the camera
	Camera(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	//Getters
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	//Setters
	public void setX(float x)
	{
		this.x=x;
	}

	public void setY(float y)
	{
		this.y=y;
	}
	
	public void tick(GameObject player)
	{
		x = -player.getX()*2 + 400;
		y = -player.getY()*2 + 300;
		
		if(x<Game.minX)
		{
			x=Game.minX;
		}else 
		if(x>Game.maxX)
		{
			x=Game.maxX;
		}

		if(y<Game.minY)
		{
			y=Game.minY;
		}else 
		if(y>Game.maxY)
		{
			y=Game.maxY;
		}
	
	
	}
}