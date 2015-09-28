package model;

import controller.Teams;

public class Stats{
	
	private int moves=0;
	private int death=0;
	private int pawn=0;
	private int check=0;
	private boolean checkmate=false;
	private Teams color;
	
	public Stats(Teams color){
		this.color = color;
	}
	
	public Teams getColor(){
		return color;
	}

	public int getMoves(){
		return moves;
	}
	
	public int getDeath(){
		return death;
	}
	
	public int getPawn(){
		return pawn;
	}
	
	public int getCheck(){
		return check;
	}
	
	public boolean getCheckmate(){
		return checkmate;
	}
	
	public void setMoves(){
		moves++;
	}
	
	public void setDeath(){
		death++;
	}
	
	public void setPawn(){
		pawn++;
	}
	
	public void setCheck(){
		check++;
	}
	
	public void setCheckmate(boolean other){
		checkmate = other;
	}

}
