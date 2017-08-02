package com.payne.game.framework;

import java.util.LinkedList;

import java.awt.Rectangle;
import java.awt.Graphics2D;

/**

This class is the structure for almost all of the objects of the Game, be it player, block or enemy
	Variable x,y									:	float variables for the position of object
	Variable velX,velY								:	float variables for the vel of objects in the corresponding direction
	Variable jumping,falling						:	tells wether the object is in jumping state or falling state
	Variable id										:	unique Id to identify the Object Type
	Variable right,left								:	tells the moevment direction of the object
	Variable crouching,shooting and slashing		: 	tells if the object is in crouch state
	Variable eright,eleft							:	parameters of objects controlled by the KeyInput class for debugging purposes
	
*/

public abstract class GameObject											
{

	protected float x,y;
	protected float velX=0, velY =0;
	protected int hp;
	protected int comboNumber = 1;
	protected boolean jumping = false;
	protected boolean falling = true;
	protected boolean right = false;
	protected boolean left = false;
	protected boolean crouching = false;
	protected boolean shooting = false;
	protected boolean slashing = false;
	public boolean eright=false, eleft=false;
	protected boolean damage=false;
	protected boolean damageRight=false;
	protected boolean upperSlashing=false;
	protected boolean downSlashing=false;
	protected boolean aerialSlashing=false;
	
	protected ObjectId id;
	
	protected BulletId bulletId;
	
	public GameObject(float x, float y, ObjectId id )									//Constructor sets the position of the Object and tells the id of the Object
	{
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	/*
		These are the abstract methods that are necessary for the object to have a unique properties
	
		tick			:		These include the tick method which is called by the Game class. It is the updation of the object
		render			:		The render method is the graphical render no.of ticks time per second
		getBounds		:		The getBounds is the method to check collision(discussed in details afterwards)
		resetIndex		:		The resetIndex resets the frames of the Object Animation
	*/
	
	public abstract void tick(LinkedList<GameObject> object);
	
	public abstract void render(Graphics2D g);

	public abstract Rectangle getBounds();
	
	public abstract Rectangle getBoundsRight();
	
	public abstract Rectangle getBoundsLeft();
	
	public abstract Rectangle getBoundsTop();
	
	public abstract Rectangle getBoundsBottom();
	
	public abstract void resetIndex();

	//Starting of the Getters. The Code is self Explanatory
	
	public int getHp()
	{
		return hp;
	}

	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}

	public  float getVelX()
	{
		return velX;
	}
	public float getVelY()
	{
		return velY;
	}
	
	public  ObjectId getId()
	{
		return id;
	}
	
	public  BulletId getBulletId()
	{
		return bulletId;
	}
	
	//Checking methods
	
	public boolean isFalling()
	{
		return falling;
	}
	
	public boolean isJumping()
	{
		return jumping;
	}
	
	public boolean isCrouching()
	{
		return crouching;
	}

	//Starting of the Setters. The Code is self Explanatory
	
	public void setRight(boolean right)
	{
		this.right=right;
	}
	
	public void setLeft(boolean left)
	{	
		this.left=left;
	}
	
	public void setCrouching(boolean crouching)
	{
		this.crouching=crouching;
	}
	
	public void setUpperSlashing(boolean upperSlashing)
	{
		this.upperSlashing=upperSlashing;
	}
	
	public void setDownSlashing(boolean downSlashing)
	{
		this.downSlashing=downSlashing;
	}
	
	
	
	
	
	public void setShooting(boolean shooting)
	{
		this.shooting=shooting;
	}
	
	public void setSlashing(boolean slashing)
	{
		this.slashing=slashing;
	}
	
	public void setAerialSlashing(boolean aerialSlashing)
	{
		this.aerialSlashing=aerialSlashing;
	}

	public void setX(float x)
	{
		this.x=x;
	}
	
	public void setY(float y)
	{
		this.y=y;
	}
	
	public void setComboNumber(int comboNumber)
	{
		this.comboNumber=comboNumber;
	}

	public void setVelX(float velX)
	{
		this.velX=velX;
	}
	public  void setVelY(float velY)
	{
		this.velY=velY;
	}
	public void setFalling(boolean falling)
	{
		this.falling=falling;
	}
	
	public void setJumping(boolean jumping)
	{
		this.jumping=jumping;
	}

	public void setHp(int hp)
	{
		this.hp=hp;
	}

	public void setDamage(boolean damage)
	{
		this.damage=damage;
	}
	
	public void setDamageRight(boolean damageRight)
	{
		this.damageRight=damageRight;
	}
	
	

}