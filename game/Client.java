package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Client
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		// Get username
		System.out.println("Input player name");
		BufferedReader username = new BufferedReader(new InputStreamReader(System.in));
		String theUsername = username.readLine().trim();

		if(theUsername != null && theUsername.length() > 0)
		{

			// Start connection to server
			Socket clientSocket = new Socket("10.10.135.212", 7531);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


			// Send add player to server
			outToServer.writeBytes("ADDPLAYER;" + theUsername + '\n');
			// Get response from server
			String response = inFromServer.readLine();


			// If server is not full
			if(!response.equals("Server full"))
			{
				int index = 0;
				String[] settings = response.split(";");
				ArrayList<Player> players = new ArrayList<Player>();

				for(; index < settings.length; index += 5)
				{
					Player p = new Player(settings[index], Integer.parseInt(settings[index+3]));
					p.setXpos(Integer.parseInt(settings[index+1]));
					p.setYpos(Integer.parseInt(settings[index+2]));
					p.setDirection(settings[index+4]);
					players.add(p);
				}


				// Create a scorelist
				ScoreList scoreList = new ScoreList(players);
				//scoreList.draw();
				scoreList.setVisible(true);


				// Gameplayet
				Gameplayer game = new Gameplayer(scoreList, players);
				Screen screen = new Screen(game.getLevel(), outToServer);
				screen.setVisible(true);

				// Draw players
				for(Player player: players)
				{
					screen.drawPlayer(player);
				}


				// IngoingClient needs gameplayer to make changes
				IngoingClient inThread = new IngoingClient(inFromServer, game, screen);

				// Shizzle on the thread
				inThread.start();
				inThread.join();
			}
			else {
				System.out.println("Server is full");
			}

			// Close connection
			clientSocket.close();

		}
	}
}