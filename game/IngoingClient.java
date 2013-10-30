package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class IngoingClient extends Thread
{
	private BufferedReader inFromServer;
	private Gameplayer game;
	private Screen screen;
	private volatile boolean connected = true;

	public IngoingClient(BufferedReader br, Gameplayer game, Screen screen)
	{
		this.inFromServer = br;
		this.game = game;
		this.screen = screen;
	}

	public void run()
	{
		while (this.connected)
		{
			try
			{
				String sentence = this.inFromServer.readLine();
				if (sentence != null)
				{
					String[] boardUpdate = sentence.split(";");
					ArrayList<Player> players = new ArrayList<Player>();

					// Find names, positions, direction and color from server
					for (int i = 0; i < boardUpdate.length - 4; i += 6)
					{
						Player p = new Player(boardUpdate[i], Integer.parseInt(boardUpdate[i + 3]));
						p.setXpos(Integer.parseInt(boardUpdate[i + 1]));
						p.setYpos(Integer.parseInt(boardUpdate[i + 2]));
						p.setDirection(boardUpdate[i + 4]);
						p.setColor(boardUpdate[i + 5]);
						players.add(p);
					}

					// Reset board
					for (Player getPlayer : this.game.players)
					{
						this.screen.undrawPlayer(getPlayer);
					}

					// Reset scorelist
					this.game.scoreList.setPlayers(players);
					this.game.scoreList.updateScoreOnScreenAll();

					// Setup new board
					this.game.players = players;

					// For-each players
					for (Player getPlayers : this.game.players)
					{
						// To draw them
						this.screen.drawPlayer(getPlayers);
					}
				}

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}