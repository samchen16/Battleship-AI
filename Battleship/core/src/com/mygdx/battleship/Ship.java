package com.mygdx.battleship;

import com.badlogic.gdx.math.Vector2;

public class Ship {
	private int width;
	private int height;
	private int topLeftX;
	private int topLeftY;
	private int bottomRightX;
	private int bottomRightY;
	public int health;
	private boolean placed;
	public Ship(int w, int h){
		width = w;
		height = h;
		placed = false;
		health = w * h;
	}
	public void setLocation(int x, int y){
		topLeftX = x;
		topLeftY = y;
		bottomRightX = x+width;
		bottomRightY = y+height;
		placed = true;
	}
	public void changeOrientation(){
		int temp = width;
		width = height;
		height = temp;
	}
	public boolean isPlaced(){
		return placed;
	}
	public int getX(){
		return topLeftX;
	}
	public int getY(){
		return topLeftY;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
