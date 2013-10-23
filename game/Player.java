package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;


public class Player {
	String name;
	int xpos;
	int ypos;
	int point;
	String direction;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private gameplayer board;

	public Player(String name, BufferedReader inFromClient, DataOutputStream outToClient, gameplayer board) {
		this.name = name;
		this.inFromClient = inFromClient;
		this.outToClient = outToClient;
		xpos = 5;
		ypos = 7;
		point = 0;
		direction = "up";
		this.board = board;
	}

	public Player(String name, int score){
		this.name = name;
		this.point = score;
	}

	public Player(String name)
	{
		this.name = name;
	}

	public gameplayer getBoard() {
		return board;
	}

	public int getPoint() {
		return point;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public BufferedReader getInFromClient() {
		return inFromClient;
	}

	public DataOutputStream getOutToClient() {
		return outToClient;
	}

	String ToString() {
		return name + "   " + point;
	}

	void addOnePoint() {
		point++;
	}

	void subOnePoint() {
		point--;
	}
}