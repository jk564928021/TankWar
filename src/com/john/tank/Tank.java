package com.john.tank;

import java.awt.*;
import java.text.BreakIterator;
import java.util.Random;

public class Tank {
	
	public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private static final int SPEED = 1;
    
    private Random random = new Random();
    
    private int x, y;

	private Dir dir = Dir.DOWN;

	private boolean moving = true;
	private TankFrame tf = null;
	private boolean living = true;
	private Group group = Group.BAD;

	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }
    public void fire() {
    	int bX = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
    	int bY = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
    }
    
    public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public int getX() {
		return x;
	}
    
    public int getY() {
		return y;
	}

    public boolean isMoving() {
        return moving;
    }

    private void move() {

        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        
        if (random.nextInt(10) > 8) {
        	this.fire();
        }
    }

    public void paint(Graphics g) { //坦克内部的paint方法，画出一个坦克  
    	if (!living) {
    		tf.tanks.remove(this);
    	}
    	
    	switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;

		default:
			break;
		}
    	
        move();
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setX(int x) {
		this.x = x;
	}

    public void setY(int y) {
		this.y = y;
	}
    
	public void die() {
		this.living = false;
	}
}
