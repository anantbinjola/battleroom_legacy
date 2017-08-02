package com.payne.game.window;

import java.awt.image.BufferedImage;
import java.awt.Graphics;



public class Animation
{

	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	public int getCount()
	{	
		return count;
	}
	
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	public void resetIndex()
	{
		count=0;
		index=0;
	}


	public Animation(int speed, BufferedImage... args)
	{
		this.speed=speed;
		images = args ;
		
		frames=args.length;
		
	}
	
	public void runAnimation()
	{	
		if(speed != 0)
		{
			index++;
			
			
			if(index>speed)
			{
				index=0; 
				nextFrame();
				
			}
			
		}else
		{
			index =0;
		}
		
		
	}
	
	
	
	
	private void nextFrame()
	{	
		if(speed!=0)
		{
			for(int i=0;i<frames;i++)
			{
				if(count == i)
				{
					currentImg=images[i];
				}
				
				
			}
			count++;
	
			if(count==frames)
			{	count=0;
			
			}
		
		}
		else
		{
			count = 0;
		}
		
	
		
	}
	
	public void setDelay(int delay)
	{
		speed = delay;
	}
	
	public void drawAnimation(Graphics g, int x, int y)
	{
		
		g.drawImage(currentImg,x,y,null);
		
		
		
	}
	
	
	
	
	
	

}