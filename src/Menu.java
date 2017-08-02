package com.payne.game.framework;

import java.awt.Graphics;

import java.awt.Color;

import java.awt.Font;

import com.payne.game.framework.KeyInput;

import com.payne.game.window.Game;


public class Menu
{

	public static boolean select=false;

	public static int index=0;

	private Graphics g;
	
	private Game game=new Game();
	
	Texture tex = Game.getInstance();
	
	public void render(Graphics g)
	{
		// g.setColor(Color.BLACK);
		// g.fillRect(0,0,800,800);
		
	
		
		g.setColor(Color.WHITE);
		// Font font = new Font("Arial",Font.BOLD,50);
		// g.setFont(font);
		
		g.setFont(new Font("Century Gothic", Font.PLAIN,60));
		
		
		
		if(!Game.isStarted)
		{
			g.drawImage(tex.menu_bg,0,0,null);
			
			
			
			// int length = (int)g.getFontMetrics().getStringBounds("BulletHell",g).getWidth();
			
			g.drawString("BulletHell", 400 - ((int)g.getFontMetrics().getStringBounds("BulletHell",g).getWidth()) / 2, 200);
			
			g.drawString("Exit", 400 - ((int)g.getFontMetrics().getStringBounds("Exit",g).getWidth()) / 2,400);
			// g.drawString("Exit",365,450);
			
		
		}else
		if(Game.isStarted)
		{
			g.drawImage(tex.pause_bg,80,60,null);
			g.drawString("Resume", 400 - ((int)g.getFontMetrics().getStringBounds("Resume",g).getWidth()) / 2,100);
			g.drawString("Restart", 400 - ((int)g.getFontMetrics().getStringBounds("Restart",g).getWidth()) / 2,250);
			g.drawString("Options", 400 - ((int)g.getFontMetrics().getStringBounds("Option",g).getWidth()) / 2,400);
			g.drawString("Exit", 400 - ((int)g.getFontMetrics().getStringBounds("Exit",g).getWidth()) / 2,550);
			
		
		}
		
		if(!Game.isStarted)
		{
			if(index ==0)
			{
				g.setColor(Color.RED);
				g.drawString("BulletHell", 400 - ((int)g.getFontMetrics().getStringBounds("BulletHell",g).getWidth()) / 2,200);
				
			}else
			if(index ==1)
			{	
				
				g.setColor(Color.RED);
				g.drawString("Exit", 400 - ((int)g.getFontMetrics().getStringBounds("Exit",g).getWidth()) / 2,400);
				
			}
			
			if(index>1)
				index=0;
			if(index<0)
				index=1;
			
			
				
			if(select&&index==0)
			{
				Game.state=Game.STATE.BULLETHELL;
			}
			
			
			if(select&&index==1)
				System.exit(0);
		
		}
		
		if(Game.isStarted)
		{
			if(index ==0)
			{
				g.setColor(Color.RED);	
				g.drawString("Resume", 400 - ((int)g.getFontMetrics().getStringBounds("Resume",g).getWidth()) / 2,100);
				
			}else
			if(index ==1)
			{	
				g.setColor(Color.RED);
				g.drawString("Restart", 400 - ((int)g.getFontMetrics().getStringBounds("Restart",g).getWidth()) / 2,250);
				
				
			}else	
			if(index==2)
			{	
				g.setColor(Color.RED);
				g.drawString("Options", 400 - ((int)g.getFontMetrics().getStringBounds("Option",g).getWidth()) / 2,400);
			
				
			}else
			if(index==3)
			{
				g.setColor(Color.RED);
				g.drawString("Exit", 400 - ((int)g.getFontMetrics().getStringBounds("Exit",g).getWidth()) / 2,550);
			}
			
			if(index>3||index<0)
				index=0;
			
			
			if(select&&index==0)
				Game.state=Game.STATE.GAME;
				
			if(select&&index==1)
			{
				Game.reset=true;
				Game.state=Game.STATE.GAME;
				
			}else Game.reset=false;
			
				
				
				
				
				
			if(select&&index==3)
				System.exit(0);
		
		}
		
		
		
		
		
	}
	
	public void tick()
	{
		
		
		
		
		
		
		
	}

	

}