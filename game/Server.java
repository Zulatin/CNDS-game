package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private static ArrayList<Player> players;
	private static gameplayer board;
	private static ScoreList scoreList;

	public static void main(String[] args)throws Exception {
		players = new ArrayList<Player>();
		scoreList = new ScoreList(players);
		board = new gameplayer(scoreList, players);
		String clientSentence;
		String sentence;
		DataOutputStream outToClient;
		ServerSocket welcomeSocket = new ServerSocket(7531);

		IngoingServer in;
		BufferedReader inFromClient;

		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			clientSentence = inFromClient.readLine();
			System.out.println(clientSentence);

			checkPlayer(clientSentence, inFromClient, outToClient);
		}
	}

	public static void checkPlayer(String action, BufferedReader br, DataOutputStream dos) throws IOException{
		String[] args = action.split(";");
		if(args[0].equals("ADDPLAYER")){
			Player player = new Player(args[1], br, dos, board);
			players.add(player);
			System.out.println(players.size());
			IngoingServer in = new IngoingServer(player, players, scoreList);
			in.start();
			//			OutgoingServer out = new OutgoingServer(player);
			System.out.println("ingoing server thread");
			sendPlayers(dos);
		}
		
	}

	public static void sendPlayers(DataOutputStream dos) throws IOException{
		String toClient ="";
		for (Player p: players){
			toClient += p.getName()+";"+p.getXpos()+";"+p.getYpos()+";"+p.getPoint()+";"+p.getDirection()+";";
		}
		System.out.println(toClient);
		dos.writeBytes(toClient + '\n');
	}
}
