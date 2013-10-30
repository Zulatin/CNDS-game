package game;

public class ShootThread extends Thread
{

	private String[][] level;
	private Player player;
	private String direction;

	public ShootThread(String[][] level, Player p, Screen s)
	{
		this.level = level;
		this.player = p;

	}

	public void run()
	{
		int x = this.player.getXpos();
		int y = this.player.getYpos();
		this.direction = this.player.getDirection();
		int counter = 0;

		if (this.direction.equals("right"))
		{
			x++;
			counter++;
			boolean found = false;
			while (!found)
			{
				// if()
			}
		}
		if (this.direction.equals("up"))
		{

		}
		if (this.direction.equals("down"))
		{

		}
		if (this.direction.equals("left"))
		{

		}
	}
}
