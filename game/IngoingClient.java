package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class IngoingClient extends Thread
{
	private BufferedReader inFromServer;
	private gameplayer game;
	private Screen screen;

	public IngoingClient(BufferedReader br, gameplayer game, Screen screen)
	{
		this.inFromServer = br;
		this.game = game;
	}

	public void run()
	{
		boolean connected = true;
		while (connected)
		{
			try
			{
				String sentence = inFromServer.readLine();
				String[] boardUpdate = sentence.split(";");
				ArrayList<Player> players = new ArrayList<Player>();

				for (int i = 1; i < Integer.parseInt(boardUpdate[0])*5-4; i += 5)
				{
					Player p = new Player(boardUpdate[i], Integer.parseInt(boardUpdate[i+3]));
					p.setXpos(Integer.parseInt(boardUpdate[i+1]));
					p.setYpos(Integer.parseInt(boardUpdate[i+2]));
					p.setDirection(boardUpdate[i+4]);
					players.add(p);
				}

				// Reset board
				screen.draw();
				// Setup new board
				game.players = players;

				for (Player p : game.players)
				{
					screen.drawPlayer(p);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}