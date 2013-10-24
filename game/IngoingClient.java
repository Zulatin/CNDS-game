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
			System.out.println(this.connected);
			try
			{
				String sentence = inFromServer.readLine();
				System.out.println("FROM SERVER INGOINGCLIENT1: " + sentence);
				if(sentence != null)
				{
					System.out.println("FROM SERVER INGOINGCLIENT2: " + sentence);
					String[] boardUpdate = sentence.split(";");
					ArrayList<Player> players = new ArrayList<Player>();

					for(int i = 1; i < Integer.parseInt(boardUpdate[0])*5-4; i += 5)
					{
						Player p = new Player(boardUpdate[i], Integer.parseInt(boardUpdate[i+3]));
						p.setXpos(Integer.parseInt(boardUpdate[i+1]));
						p.setYpos(Integer.parseInt(boardUpdate[i+2]));
						p.setDirection(boardUpdate[i+4]);
						players.add(p);
					}

					// Reset board
					for (Player p : game.players)
					{
						System.out.println("Resetting board");
						screen.undrawPlayer(p);
					}

					// Setup new board
					game.players = players;

					for (Player p : game.players)
					{
						screen.drawPlayer(p);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}