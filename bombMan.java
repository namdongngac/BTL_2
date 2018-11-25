package GameObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class bombMan {
	
	
	private float speedX;
	private float speedY;
	public static int DIR_LEFT;
	public static int DIR_RIGHT;
	public static int DIR_UP;
	public static int DIR_DOWN;
	private int direction;//hướng chuyển động
	GameWord gameWord;
	
	
	
	public float getSpeedX() {
		return speedX;
	}
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	
	private float posX;
	private float posY;
	
	private float width;
	private float height;
	private float mass;
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		this.mass = mass;
	}
	public bombMan(float posX, float posY, float width, float height, float mass,GameWord gameWord) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.mass = mass;
		this.gameWord = gameWord;
	}
	public Rectangle getBoundForColisionWithMap() {
		Rectangle bound = new Rectangle();
		bound.x = (int) (getPosX()-getWidth()/2);
		bound.y = (int) (getPosY()-getHeight()/2);
		bound.width = (int) getWidth();
		bound.height = (int) getHeight();
		return bound;
	}
	public void update() {
		setPosX(getPosX()+speedX);
		Rectangle futureRect = getBoundForColisionWithMap();
		futureRect.y += getSpeedY();
		Rectangle rectLand = gameWord.physicalMap.haveCollisionWithLand(futureRect);
		
		if(rectLand!=null)
			setPosY(rectLand.y- getHeight()/2);
		else {
			setPosY(getPosY()+speedY);
			//setSpeedY(getSpeedY()+mass);
		}
		
	}
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect((int)(posX-getWidth()/2), (int)(posY-getHeight()/2), (int)width, (int)height);
		g2.setColor(Color.black);
		g2.fillRect((int) posX, (int) posY, 2,2);
	}

}
