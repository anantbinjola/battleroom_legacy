package com.payne.game.framework;

import com.payne.game.window.Handler;

import com.payne.game.window.Game;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyInput extends KeyAdapter
{
	public static boolean response=true;
	
	// public static boolean toggleWeapon=true;
	
	
	
	
	
	GameObject tempObject=null;
	
	Menu menu = new Menu();

	Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler= handler;
	}
	

	public void keyPressed(KeyEvent e)
	{
		
		
		int key = e.getKeyCode();
		
		if(Game.state==Game.STATE.GAME)		
		if(response)
		for(int i=0;i<handler.object.size();i++)
		{
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player1)
			{	
	
				
				
				if(key==KeyEvent.VK_DOWN) {
					
					if(tempObject.getVelY()==0)
						tempObject.setCrouching(true);
				
				}else
				if(key==KeyEvent.VK_UP && !tempObject.isJumping()) 
				{	
					tempObject.setJumping(true);
					tempObject.setVelY(-14);
					
				
				}
				
				if(key==KeyEvent.VK_RIGHT) 
				{
					tempObject.setRight(true);
				}else
				if(key==KeyEvent.VK_LEFT) 
				{
					tempObject.setLeft(true);
				}
				
			/* 	if(key == KeyEvent.VK_D )
				{	
					
					
					if(!tempObject.isJumping() &&!oneAttack)
					{	
						tempObject.resetIndex();
						tempObject.setSlashing(true);
						tempObject.setUpperSlashing(true);
						tempObject.setJumping(true);
						tempObject.setVelY(-15);
						
						oneAttack = true;
						response = false;
						tempObject.setLeft(false);
						tempObject.setRight(false);
						tempObject.setCrouching(false);
					}
					
					
					
					
				}else			
				if(key == KeyEvent.VK_F )
				{
					
					
					if(tempObject.isJumping() && !oneAttack)
					{
						tempObject.resetIndex();
						tempObject.setSlashing(true);
						tempObject.setUpperSlashing(false);
						tempObject.setDownSlashing(true);
						tempObject.setVelY(30);
						oneAttack = true;
						response = false;
						tempObject.setLeft(false);
						tempObject.setRight(false);
						tempObject.setCrouching(false);
					}
					
					
					
				}else */
				if(key==KeyEvent.VK_Z)	 
				{
					
					
				
				
					if(weaponIndex<=noW&&weaponIndex>=0)
					{
						
						//tempObject.resetIndex();
						
						if(weaponIndex==0)
						{
							tempObject.setShooting(true); 
							tempObject.resetIndex();
							response = false;
							tempObject.setLeft(false);
							tempObject.setRight(false);
							tempObject.setCrouching(false);
						}else
						
						if(weaponIndex==1 && !oneAttack)
						{
							if(tempObject.isJumping())
							{
								tempObject.setAerialSlashing(true);
							}
							tempObject.setSlashing(true); 
							tempObject.resetIndex();
							oneAttack = true;
							
							tempObject.setLeft(false);
							tempObject.setRight(false);
							tempObject.setCrouching(false);
							response = false;
						}
						
						
						
						
						
						
						
					}
					
				}
				
				
				
				if(key==KeyEvent.VK_A)	
				{
					
					weaponIndex--;
					
				}
				if(key==KeyEvent.VK_S)	
				{
					weaponIndex++;
					
				}
				
				if(weaponIndex>noW)
					weaponIndex=0;
				else
				if(weaponIndex<0)
					weaponIndex=noW;
					
				
				
				
				break;
				
			}
		 
		}
		 
			if(Game.state==Game.STATE.MENU)		
			{	
				Menu.select=false;
				
				if(key==KeyEvent.VK_DOWN) 
				{
					Menu.index++;
					
				}
				

				if(key==KeyEvent.VK_UP) 
				{
					Menu.index--;
				}
				
				if(key==KeyEvent.VK_ENTER)
				{	
				
					Menu.select=true;
			
				}
	
			}
			
		if(key==KeyEvent.VK_RIGHT)tempObject.eright = true; else
		if(key==KeyEvent.VK_LEFT) tempObject.eleft = true;
		

		
		
		
		
		
		
		if(key==KeyEvent.VK_ESCAPE)
		{		
			Menu.index=0;
			
			if(Game.isStarted)
			{
				if(Game.state==Game.STATE.MENU)
					Game.state=Game.STATE.GAME;
				else
				if(Game.state==Game.STATE.GAME)
					Game.state=Game.STATE.MENU;
			}
			else
			{
				System.exit(0);
			}
		
		}
		
		
	
	
	}
	
	public void keyReleased(KeyEvent e)
	{	
		int key = e.getKeyCode();
		Menu.select=false;
		
		if(key==KeyEvent.VK_Z||key==KeyEvent.VK_D ||key==KeyEvent.VK_F ) 
		{
			oneAttack = false;
		}

		if(Game.state==Game.STATE.GAME)
		if(response)	
		for(int i=0;i<handler.object.size();i++)
		{
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player)
			{
				
				if(key==KeyEvent.VK_DOWN) {tempObject.setCrouching(false);}

				if(key==KeyEvent.VK_RIGHT) {tempObject.setRight(false);tempObject.setVelX(0);}else
				if(key==KeyEvent.VK_LEFT) {tempObject.setLeft(false);tempObject.setVelX(0);}else
				if(key==KeyEvent.VK_Z) 
				{
					
					
					//tempObject.resetIndex();
					tempObject.setShooting(false);
					//tempObject.setSlashing(false);
				}
				
				break;
			}
			
		}
		
		if(key==KeyEvent.VK_RIGHT)tempObject.eright=false; else
		if(key==KeyEvent.VK_LEFT) tempObject.eleft=false;
		
		
		if(Game.state==Game.STATE.MENU)		
			{	
				
				if(key==KeyEvent.VK_DOWN) 
				{
					
				}
		

				if(key==KeyEvent.VK_UP) 
				{
					
				}
				
				
				if(key==KeyEvent.VK_E)
				{	
				
				}
		
			}
			
	
	
	}

}




