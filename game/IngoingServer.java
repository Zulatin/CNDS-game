package game;

import java.io.IOException;

public class IngoingServer extends Thread
{
	private Player player;
	private String direction = "left", wall = "w", command = "";

	public IngoingServer(Player player)
	{
		this.player = player;
	}

	public void run()
	{
		while(true)
		{
			try {
				// Split string from client
				String[] message = player.getInFromClient().readLine().split(";");

				// Find direction and command
				direction = message[1];
				command = message[0];
			} catch (IOException e) {
				e.printStackTrace();
			}


			if (command.equals("MOVEPLAYER"))
			{
				if (direction != null)
				{
					int x = player.getXpos(), y = player.getYpos();

					if (direction.equals("right")) {
						x = player.getXpos() + 1;
					}
					if (direction.equals("left")) {
						x = player.getXpos() - 1;
					}
					if (direction.equals("up")) {
						y = player.getYpos() - 1;
					}
					if (direction.equals("down")) {
						y = player.getYpos() + 1;
					}

					String[][] level = player.getBoard().getLevel();
					if (level[x][y].equals(wall))
					{

						// Take a point from player
						player.subOnePoint();
						// Update scoreboard
						Server.scoreList.updateScoreOnScreenAll();

					} else {

						// Give a point to player
						player.addOnePoint();
						// Update scoreboard
						Server.scoreList.updateScoreOnScreenAll();


						// Create toClient string
						String toClient = "";
						player.setXpos(x);
						player.setYpos(y);

						for (Player getPlayer: Server.players)
						{
							toClient += getPlayer.getName() + ";" + getPlayer.getXpos() + ";" + getPlayer.getYpos() + ";" + getPlayer.getPoint() + ";" + getPlayer.getDirection() + ";";
						}


						try
						{

							// For-each players and write players to them
							for (Player getPlayers: Server.players)
								getPlayers.output(toClient);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}

			if (command.equals("REMOVEPLAYER"))
			{

			}
		}
	}
}