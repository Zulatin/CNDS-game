package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class Player
{
	String name, direction = "up", color;
	int xpos = 5, ypos = 7, point = 0;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private Gameplayer board;

	public Player(String name, BufferedReader inFromClient, DataOutputStream outToClient, Gameplayer board, String color)
	{
		this.name = name;
		this.inFromClient = inFromClient;
		this.outToClient = outToClient;
		this.board = board;
		this.color = color;
	}

	public Player(String name, int score)
	{
		this.name = name;
		this.point = score;
	}

	public Player(String name)
	{
		this.name = name;
	}

	public Gameplayer getBoard()
	{
		return this.board;
	}

	public int getPoint()
	{
		return this.point;
	}

	public int getXpos()
	{
		return this.xpos;
	}

	public void setXpos(int xpos)
	{
		this.xpos = xpos;
	}

	public int getYpos()
	{
		return this.ypos;
	}

	public void setYpos(int ypos)
	{
		this.ypos = ypos;
	}

	public String getDirection()
	{
		return this.direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public String getName()
	{
		return this.name;
	}

	public BufferedReader getInFromClient()
	{
		return this.inFromClient;
	}

	public DataOutputStream getOutToClient()
	{
		return this.outToClient;
	}

	public String toString()
	{
		return this.name + "   " + this.point;
	}

	void addOnePoint()
	{
		this.point++;
	}

	void subOnePoint()
	{
		this.point--;
	}

	void shotPlayer()
	{
		this.point++;
	}

	public String getColor()
	{
		return this.color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public synchronized void output(String toClient) throws IOException
	{
		this.outToClient.writeBytes(toClient + '\n');
	}

	public void resetScore()
	{
		this.point = 0;
	}
}