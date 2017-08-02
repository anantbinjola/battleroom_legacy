package com.payne.game.framework;

import java.awt.image.BufferedImage;

import com.payne.game.window.BufferedImageLoader;

import com.payne.game.window.Game;


/**
	This class loads the maximum images which are used in the whole Game
	
	This class works on the principle that one image can contain many subImages. The subimages can be used for creating animations.
	So the animations are created in the form of spritesheets
	A spritesheet is an image which contains all the frames involved in an animation
	The subimages are grabbed with the help of getSubImage method which takes the row and column number to extract the subimage from a spritesheet
	
*/


public class Texture
{	

	//Spritesheets
	SpriteSheet bs,ps,fs,pjs,cs,ss1,bull1s,bull2s,slashCombo3Sheet,
	ds,bjs,bandits,bfs,archers,afs,ajs,banditStabs,archerShoot0s,archerShoot45s, 
	arrows,slashCombo2Sheet,slashCombo1Sheet,upperSlashSheet,downSlashSheet,
	upperTrailSheet,downTrailSheet,aerialSlashSheet,archerDamageSheet, dropSlashStartSheet,dropSlashEndSheet,dropSlashCenterSheet,
	upSlashStartSheet,upSlashEndSheet,upSlashCenterSheet;
	
	
	
	
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage bandit_sheet = null;
	private BufferedImage playerJump_sheet = null;
	private BufferedImage banditJump_sheet = null;
	private BufferedImage banditFall_sheet = null;
	private BufferedImage archerJump_sheet = null;
	private BufferedImage archerFall_sheet = null;
	private BufferedImage banditStab_sheet = null;
	private BufferedImage fall_sheet = null;
	private BufferedImage crouch_sheet = null;
	private BufferedImage shoot_sheet = null;
	private BufferedImage slashCombo3_sheet = null;
	private BufferedImage bullet1_sheet = null;
	private BufferedImage bullet2_sheet = null;
	
	private BufferedImage upperSlash_sheet = null;
	
	private BufferedImage downSlash_sheet = null;
	
	private BufferedImage dropSlashStart_sheet = null;
	private BufferedImage dropSlashEnd_sheet = null;
	private BufferedImage dropSlashCenter_sheet = null;
	
	private BufferedImage upSlashStart_sheet = null;
	private BufferedImage upSlashEnd_sheet = null;
	private BufferedImage upSlashCenter_sheet = null;
	
	private BufferedImage slashCombo2_sheet = null;
	
	private BufferedImage slashCombo1_sheet = null;
	
	private BufferedImage aerialSlash_sheet = null;
	
	private BufferedImage archerShoot0_sheet = null;
	
	private BufferedImage archerShoot45_sheet = null;
	
	
	
	private BufferedImage archer_sheet = null;

	private BufferedImage damage_sheet = null;
	
	private BufferedImage archerdamage_sheet = null;
	
	private BufferedImage arrow_sheet = null;
	
	
	//weapon's image array
	public BufferedImage weaponIcon[] = new BufferedImage[2];
	
	public BufferedImage slashCombo2[] = new BufferedImage[16];
	
	public BufferedImage slashCombo1[] = new BufferedImage[16];
	
	public BufferedImage aerialSlash[] = new BufferedImage[16];

	public BufferedImage upperSlash[] = new BufferedImage[18];
	
	public BufferedImage upperTrail[] = new BufferedImage[2];
	
	public BufferedImage downSlash[] = new BufferedImage[18];
	
	public BufferedImage dropSlash[] = new BufferedImage[18];
	
	public BufferedImage upSlash[] = new BufferedImage[18];
	
	
	
	public BufferedImage downTrail[] = new BufferedImage[2];
	
	
	
	public BufferedImage menu_bg = null;

	public BufferedImage pause_bg = null;
	
	
	
	
	public BufferedImage[] block=new BufferedImage[2];
	
	public BufferedImage[] arrow=new BufferedImage[2];
	
	public BufferedImage[] player = new BufferedImage[18];
	
	public BufferedImage[] bandit = new BufferedImage[18];
	
	public BufferedImage[] archer = new BufferedImage[18];
	
	public BufferedImage[] archerShoot0 = new BufferedImage[16];
	
	public BufferedImage[] archerShoot45 = new BufferedImage[16];
	
	public BufferedImage[] shoot = new BufferedImage[18];
	public BufferedImage[] slashCombo3 = new BufferedImage[18];
	public BufferedImage[] bullet1 = new BufferedImage[4];
	public BufferedImage[] bullet2 = new BufferedImage[4];
	
	public BufferedImage[] playerJump = new BufferedImage[2];
	public BufferedImage[] banditJump = new BufferedImage[2];
	public BufferedImage[] banditFall = new BufferedImage[2];
	public BufferedImage[] banditStab = new BufferedImage[18];
	public BufferedImage[] archerJump = new BufferedImage[2];
	public BufferedImage[] archerFall = new BufferedImage[2];
	public BufferedImage[] fall = new BufferedImage[2];
	public BufferedImage[] crouch = new BufferedImage[2];
	public BufferedImage[] damage = new BufferedImage[2];
	
	public BufferedImage archer_damage[] = new BufferedImage[2];
	
	
	
	
	public Texture()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
		
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/Player/player_sheet.png");			
			playerJump_sheet = loader.loadImage("/Player/player_jump.png");
			banditJump_sheet = loader.loadImage("/Bandit/bandit_jump.png");
			banditStab_sheet = loader.loadImage("/Bandit/bandit_stab.png");
			banditFall_sheet = loader.loadImage("/Bandit/bandit_fall.png");
			fall_sheet = loader.loadImage("/Player/falling.png");
			crouch_sheet = loader.loadImage("/Player/crouching.png");
			shoot_sheet = loader.loadImage("/Player/shot1_sheet.png");
			bullet1_sheet= loader.loadImage("/Player/bullet1_sheet.png");
			bullet2_sheet= loader.loadImage("/Player/bullet2_sheet.png");
			arrow_sheet = loader.loadImage("/Archer/arrow_sheet.png");
			bandit_sheet = loader.loadImage("/Bandit/bandit_sheet.png");
			
			dropSlashStart_sheet = loader.loadImage("/Player/Drop_Slash/dropslash_start.png");
			dropSlashCenter_sheet = loader.loadImage("/Player/Drop_Slash/dropslash_center.png");
			dropSlashEnd_sheet = loader.loadImage("/Player/Drop_Slash/dropslash_end.png");
			
			upSlashStart_sheet = loader.loadImage("/Player/up_Slash/upslash_start.png");
			upSlashCenter_sheet = loader.loadImage("/Player/up_Slash/upslash_center.png");
			upSlashEnd_sheet = loader.loadImage("/Player/up_Slash/upslash_end.png");
			
			
			
			damage_sheet = loader.loadImage("/Player/damage.png");
			archer_sheet = loader.loadImage("/Archer/archer_sheet.png");
			archerJump_sheet = loader.loadImage("/Archer/archer_jump.png");
			archerFall_sheet = loader.loadImage("/Archer/archer_fall.png");
			archerShoot0_sheet = loader.loadImage("/Archer/archer_shoot0.png");
			archerShoot45_sheet = loader.loadImage("/Archer/archer_shoot45.png");
			
			slashCombo3_sheet = loader.loadImage("/Player/slash_sheet3.png");
			
			aerialSlash_sheet = loader.loadImage("/Player/aerial_slash.png");
			
			slashCombo2_sheet = loader.loadImage("/Player/slash_sheet2.png");
			
			slashCombo1_sheet = loader.loadImage("/Player/slash_sheet1.png");
			
			upperSlash_sheet = loader.loadImage("/Player/upper_slash.png");
			
			downSlash_sheet = loader.loadImage("/Player/down_slash.png");
			
			weaponIcon[0] = loader.loadImage("/HUD/gun.png");
			
			weaponIcon[1] = loader.loadImage("/HUD/sword.png");
			
			menu_bg = loader.loadImage("/Cutscene/menu_bg.png");
			
			pause_bg = loader.loadImage("/Cutscene/pause_bg.png");
			
			
	
			
			
		}
		catch(Exception e){e.printStackTrace();}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		arrows = new SpriteSheet(arrow_sheet);
		pjs = new SpriteSheet(playerJump_sheet);
		fs = new SpriteSheet(fall_sheet);
		cs = new SpriteSheet(crouch_sheet);
		ss1 = new SpriteSheet(shoot_sheet);
		bull1s=new SpriteSheet(bullet1_sheet);
		bull2s=new SpriteSheet(bullet2_sheet);
		
		slashCombo2Sheet = new SpriteSheet(slashCombo2_sheet);
		
		aerialSlashSheet = new SpriteSheet(aerialSlash_sheet);
		
		slashCombo1Sheet = new SpriteSheet(slashCombo1_sheet);
		
		upperSlashSheet = new SpriteSheet(upperSlash_sheet);
		
		downSlashSheet = new SpriteSheet(upperSlash_sheet);
		
		dropSlashStartSheet = new SpriteSheet(dropSlashStart_sheet);
		dropSlashCenterSheet = new SpriteSheet(dropSlashCenter_sheet);
		dropSlashEndSheet = new SpriteSheet(dropSlashEnd_sheet);
		
		upSlashStartSheet = new SpriteSheet(upSlashStart_sheet);
		upSlashCenterSheet = new SpriteSheet(upSlashCenter_sheet);
		upSlashEndSheet = new SpriteSheet(upSlashEnd_sheet);
		
		
		
		slashCombo3Sheet = new SpriteSheet(slashCombo3_sheet);
		ds = new SpriteSheet(damage_sheet);
		
		bandits = new SpriteSheet(bandit_sheet);
		bjs = new SpriteSheet(banditJump_sheet);
		bfs = new SpriteSheet(banditFall_sheet);
		
		bfs = new SpriteSheet(banditFall_sheet);
		
		banditStabs = new SpriteSheet(banditStab_sheet);
		
		archers = new SpriteSheet(archer_sheet);
		ajs = new SpriteSheet(archerJump_sheet);
		afs = new SpriteSheet(archerFall_sheet);
		archerShoot0s = new SpriteSheet(archerShoot0_sheet);
		archerShoot45s = new SpriteSheet(archerShoot45_sheet);
		
		
		getTextures();
		
		
	}
	
	private void getTextures()
	{
	
		
		
		block[0] = bs.grabImage(1,1,32,32);
		
		block[1] = bs.grabImage(2,1,32,32);
		
		player[0] = ps.grabImage(1,1,32,48); // idle frame for player
		
		bandit[0] = bandits.grabImage(1,1,32,48); // idle frame for enemy
		
		archer[0] = archers.grabImage(1,1,32,48); // idle frame for enemy
		
		arrow[0] = arrows.grabImage(1,1,23,3);
		arrow[1] = arrows.grabImage(2,1,23,3);
		
		
		
		
		playerJump[0] = pjs.grabImage(1,1,32,48);
		playerJump[1] = pjs.grabImage(2,1,32,48);
		
		banditJump[0] = bjs.grabImage(1,1,32,48);
		banditJump[1] = bjs.grabImage(2,1,32,48);
		
		banditFall[0] = bfs.grabImage(1,1,32,48);
		banditFall[1] = bfs.grabImage(2,1,32,48);
		
		archerJump[0] = ajs.grabImage(1,1,32,48);
		archerJump[1] = ajs.grabImage(2,1,32,48);
		
		archerFall[0] = afs.grabImage(1,1,32,48);
		archerFall[1] = afs.grabImage(2,1,32,48);
		
		
		fall[0] = fs.grabImage(1,1,32,48);
		fall[1] = fs.grabImage(2,1,32,48);
		
		crouch[0] = cs.grabImage(1,1,32,32);
		crouch[1] = cs.grabImage(2,1,32,32);
		
		damage[0] = ds.grabImage(1,1,32,48);
		damage[1] = ds.grabImage(2,1,32,48);
		
		
		
		
		for(int i = 1;i<=16;i++)
		{
			shoot[i-1] = ss1.grabImage(i,1,48,48);
		}
		
		for(int i = 1;i<=16;i++)
		{
			archerShoot0[i-1] = archerShoot0s.grabImage(i,1,48,48);
		}
		
		for(int i = 1;i<=16;i++)
		{
			archerShoot45[i-1] = archerShoot45s.grabImage(i,1,48,48);
		}
		
		
		for(int i = 1;i<=16;i++)
		{
			slashCombo1[i-1] = slashCombo1Sheet.grabImage(i,1,64,64);
		}
		
		for(int i = 1;i<=16;i++)
		{
			slashCombo2[i-1] = slashCombo2Sheet.grabImage(i,1,64,64);
			
		}
		
		for(int i = 1;i<=16;i++)
		{
			aerialSlash[i-1] = aerialSlashSheet.grabImage(i,1,64,64);
			
		}
		
		for(int i = 1;i<=16;i++)
		{
			slashCombo3[i-1] = slashCombo3Sheet.grabImage(i,1,64,64);
			
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(int i = 1;i<=8;i++)
		{
			dropSlash[i-1] = dropSlashStartSheet.grabImage(i,1,64,64);
		}
		
		dropSlash[8] = dropSlashCenterSheet.grabImage(1,1,64,64);
		
		dropSlash[9] = dropSlashCenterSheet.grabImage(2,1,64,64);
		
		
		for(int i = 11;i<=18;i++)
		{
			dropSlash[i-1] = dropSlashEndSheet.grabImage(i-10,1,64,64);
		}
		
		
		
		
		
		
		for(int i = 1;i<=8;i++)
		{
			upSlash[i-1] = upSlashStartSheet.grabImage(i,1,64,64);
		}
		
		upSlash[8] = upSlashCenterSheet.grabImage(1,1,64,64);
		
		upSlash[9] = upSlashCenterSheet.grabImage(2,1,64,64);
		
		
		for(int i = 1;i<=8;i++)
		{
			upSlash[9-i] = upSlashEndSheet.grabImage(i,1,64,64);
		}
		
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		for(int i = 1;i<=9;i++)
		{
			upperSlash[i-1] = upperSlashSheet.grabImage(1,10-i,64,64);
			
		}
		
		for(int i = 10;i<=18;i++)
		{
			upperSlash[i-1] = upperSlashSheet.grabImage(2,i-9,64,64);
			
		}
		
		for(int i = 1;i<=9;i++)
		{
			downSlash[i-1] = downSlashSheet.grabImage(1,10-i,64,64);
			
		}
		
		for(int i = 10;i<=18;i++)
		{
			downSlash[i-1] = downSlashSheet.grabImage(2,i-9,64,64);
			
		}
		
		
		
		
	
		
		for(int i = 1;i<=10;i++)
		{
			banditStab[i-1] = banditStabs.grabImage(i,1,48,48);
		}
		
		
		
		
		
		
		
		for(int i = 1;i<=4;i++)
		{
			bullet1[i-1] = bull1s.grabImage(i,1,16,8);
		}
		
		for(int i = 1;i<=4;i++)
		{
			bullet2[i-1] = bull2s.grabImage(i,1,16,8);
		}
		
		
	
		
		for(int i=2;i<=7;i++)
		{
			player[i-1] = ps.grabImage(i,1,32,48); // walkingRight animation
		
		}
		
		for(int i=10;i<=16;i++)
		{
			player[i-1] = ps.grabImage(i,1,32,48); // walkingLeft animation
		
		}
		
		
		for(int i=2;i<=7;i++)
		{
			bandit[i-1] = bandits.grabImage(i,1,32,48); // walkingRight animation
		
		}
		
		for(int i=8;i<=14;i++)
		{
			bandit[i-1] = bandits.grabImage(i,1,32,48); // walkingLeft animation
		
		}
		
		for(int i=2;i<=7;i++)
		{
			archer[i-1] = archers.grabImage(i,1,32,48); // walkingRight animation
		
		}
		
		for(int i=8;i<=14;i++)
		{
			archer[i-1] = archers.grabImage(i,1,32,48); // walkingLeft animation
		
		}
		
		
		
		
		
		
	}
	
	
}