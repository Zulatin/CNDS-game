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

			checkPlayer(clientSentence, inFromClient, outToClient);
		}
	}

	public static void checkPlayer(String action, BufferedReader br, DataOutputStream dos) throws IOException{
		String[] args = action.split(";");
		if(args[0].equals("ADDPLAYER")){
			Player player = new Player(args[1], br, dos, board);
			players.add(player);
			IngoingServer in = new IngoingServer(player, players, scoreList);
			//			OutgoingServer out = new OutgoingServer(player);
			sendPlayers(dos);
		}
		else if(args[0].equals("MOVEPLAYER")){
			for(Player p : players){
				if(p.getName().equals(args[0])){
					//TODO
				}
			}
		}
		else if(args[0].equals("REMOVE")){
			//TODO
		}
	}

	public static void sendPlayers(DataOutputStream dos) throws IOException{
		String toClient ="";
		for (Player p: players){
			toClient += p.getName()+";"+p.getXpos()+";"+p.getYpos()+";"+p.getPoint()+";"+p.getDirection()+";";
		}
		dos.writeBytes(toClient);
	}
}
