package com.john.tank;

import java.awt.*;

public class Tank {

    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;


    private TankFrame tf = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }



    public Tank(int x, int y, Dir dir, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) { //坦克内部的paint方法，画出一个坦克

        Color c = g.getColor(); //保存原来画笔的颜色
        g.setColor(Color.YELLOW);  //坦克的颜色
        g.fillRect(x, y,50, 50);
        g.setColor(c);


        move();
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

    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
    }
}
