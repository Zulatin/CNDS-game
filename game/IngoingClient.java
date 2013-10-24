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
				String sentence = inFromServer.readLine();
				if(sentence != null)
				{
					String[] boardUpdate = sentence.split(";");
					ArrayList<Player> players = new ArrayList<Player>();

					for(int i = 0; i < boardUpdate.length-4; i += 5)
					{
						Player p = new Player(boardUpdate[i], Integer.parseInt(boardUpdate[i+3]));
						p.setXpos(Integer.parseInt(boardUpdate[i+1]));
						p.setYpos(Integer.parseInt(boardUpdate[i+2]));
						p.setDirection(boardUpdate[i+4]);
						players.add(p);
					}


					// Reset board - player
					for (Player player: game.players)
					{
						// Undraw player
						screen.undrawPlayer(player);
					}


					// Reset scorelist
					this.game.scoreList.setPlayers(players);
					this.game.scoreList.draw();
					this.game.scoreList.updateScoreOnScreenAll();


					// Setup new board
					game.players = players;


					// Run through players
					for (Player player: game.players)
					{
						// Draw player
						screen.drawPlayer(player);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}