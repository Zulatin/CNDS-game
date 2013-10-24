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
		String clientSentence;
		DataOutputStream outToClient;
		welcomeSocket = new ServerSocket(7531);
		BufferedReader inFromClient;

		while(true)
		{
			Socket connectionSocket = welcomeSocket.accept();

			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			clientSentence = inFromClient.readLine();

			checkPlayer(clientSentence, inFromClient, outToClient);
		}
	}

	public static void checkPlayer(String action, BufferedReader br, DataOutputStream dos) throws IOException, InterruptedException
	{
		String[] args = action.split(";");

		if(args[0].equals("ADDPLAYER") && args[1] != null)
		{
			Player player = new Player(args[1], br, dos, board);
			players.add(player);

			IngoingServer in = new IngoingServer(player);
			in.start();

			sendPlayers(dos);
		}

	}

	public static void sendPlayers(DataOutputStream dos) throws IOException
	{
		String toClient = "";

		for (Player player: players)
		{
			toClient += player.getName() + ";" + player.getXpos() + ";" + player.getYpos() + ";" + player.getPoint() + ";" + player.getDirection() + ";";
		}

		dos.writeBytes(toClient + '\n');
	}
}
