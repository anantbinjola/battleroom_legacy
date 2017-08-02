package com.payne.game.window;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.DisplayMode;
import javax.swing.JFrame;



public class Window 
{	
	
	
	
	private DisplayMode newDisplayMode;
	
	public Window(int w, int h, String title, Game game)
	{
		
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		

		
		
		frame.setLocationRelativeTo(null);
		
		
		
		frame.setUndecorated(true);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		GraphicsDevice gd = gs[0];
		
		
		
		
		DisplayMode oldDisplayMode = gd.getDisplayMode();
		
		DisplayMode dm = new DisplayMode(800, 600, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		
		
		try {
			if(gd.isFullScreenSupported())
			{
				gd.setFullScreenWindow(frame);
				gd.setDisplayMode(dm);
			}
 
		}catch(Exception e) 
		{
			gd.setFullScreenWindow(null);
		}
		
		
		
		//frame.setVisible(true);
		frame.pack();	
		
		
		
		
		game.start();
		
	}















}