package com.payne.game.window;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BufferedImageLoader
{
	private BufferedImage image;									//loading image 
	
	public BufferedImage loadImage(String path)						//method to load the image to be loaded in variable image of type BufferedImage
	{		
		System.out.println(path);									//testing statement to check whether the image is loaded or not
		
		try
		{
			image = ImageIO.read(getClass().getResource(path));		//read Image from the path getClass().getResource(path)
		}
		catch(IOException e)
		{
			e.printStackTrace();									//statement used to identify the root of the Exception
		}
		
		return image;												//return the obtained image
	}
}