package game;

import java.io.IOException;

public class IngoingServer extends Thread
{
	private Player player;
	private String command = "", content = "left", wall = "w";

	public IngoingServer(Player player)
	{
		this.player = player;
	}

	public void run()
	{
		while(true)
		{
			try
			{

				// Get clients message
				String[] message = player.getInFromClient().readLine().split(";");

				// Get command and content
				command = message[0];
				content = message[1];

			} catch (IOException e) {
				e.printStackTrace();
			}

			// Check if content is not null
			if(content != null)
			{
				if (command.equals("MOVEPLAYER"))
				{
					int x = player.getXpos(), y = player.getYpos();

					if (content.equals("right")) {
						x = player.getXpos() + 1;
					}
					if (content.equals("left")) {
						x = player.getXpos() - 1;
					}
					if (content.equals("up")) {
						y = player.getYpos() - 1;
					}
					if (content.equals("down")) {
						y = player.getYpos() + 1;
					}

					String[][] level = player.getBoard().getLevel();
					if (level[x][y].equals(wall))
					{

						// Take a point from player
						player.subOnePoint();
						// Update score board
						Server.scoreList.updateScoreOnScreenAll();

					} else {

						// Add point to player
						player.addOnePoint();
						// Update score board
						Server.scoreList.updateScoreOnScreenAll();

						// Create string being sent to client
						String toClient = "";

						// Update players positions
						player.setXpos(x);
						player.setYpos(y);

						// Run through players
						for (Player player: Server.players)
						{
							toClient = player.getName() + ";" + player.getXpos() + ";" + player.getYpos() + ";" + player.getPoint() + ";" + player.getDirection() + ";";
						}

						try
						{
							// Run through players
							for (Player player: Server.players)
							{
								player.output(toClient);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}


				if (command.equals("REMOVEPLAYER"))
				{

				}
			}
		}
	}
}
