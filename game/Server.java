package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	public static ArrayList<Player> players;
	public static ScoreList scoreList;
	private static Gameplayer board;
	private static ServerSocket welcomeSocket;

	public static void main(String[] args) throws Exception
	{
		players = new ArrayList<Player>();
		scoreList = new ScoreList(players);
		board = new Gameplayer(scoreList, players);

		welcomeSocket = new ServerSocket(7531);

		DataOutputStream outToClient;
		BufferedReader inFromClient;

		while (true)
		{
			// Accept new connections
			Socket connectionSocket = welcomeSocket.accept();

			// Read and in
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			if (players.size() < 4)
			{
				// Check player
				checkPlayer(inFromClient.readLine(), inFromClient, outToClient);
			}
			else
			{
				outToClient.writeBytes("SERVERFULL\n");
			}
		}
	}

	public static void checkPlayer(String action, BufferedReader br, DataOutputStream dos) throws IOException, InterruptedException
	{
		// Split action
		String[] args = action.split(";");

		if (args[0].equals("ADDPLAYER") && args[1] != null && args[1].trim().length() > 0)
		{
			// Find out players color
			String color = "";
			if (players.size() == 0)
			{
				color = "red";
			}
			if (players.size() == 1)
			{
				color = "blue";
			}
			if (players.size() == 2)
			{
				color = "purple";
			}
			if (players.size() == 3)
			{
				color = "green";
			}

			// Create new player
			Player player = new Player(args[1].trim(), br, dos, board, color);
			players.add(player);

			// Start new ingoing server
			IngoingServer in = new IngoingServer(player);
			in.start();

			// Send to players that we have a new client
			sendPlayers(dos);
		}
	}

	public static void sendPlayers(DataOutputStream dos) throws IOException
	{
		String toClient = "";

		// Run through our players
		for (Player player : players)
		{
			// Create string with players
			toClient += player.getName() + ";" + player.getXpos() + ";" + player.getYpos() + ";" + player.getPoint() + ";" + player.getDirection() + ";" + player.getColor() + ";";
		}

		// And send that string to the player
		dos.writeBytes(toClient + '\n');
	}
}