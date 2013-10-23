package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private static ArrayList<Player> players;
	private static gameplayer board;
	
	public static void main(String[] args)throws Exception {
		players = new ArrayList<Player>();
		ScoreList scoreList = new ScoreList(players);
		board = new gameplayer(scoreList, players);
		String clientSentence;
		String sentence;
		DataOutputStream outToClient;
		ServerSocket welcomeSocket = new ServerSocket(7531);

		IngoingServer in;
		OutgoingServer out;
		BufferedReader inFromClient;
		
		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			clientSentence = inFromClient.readLine();
			
			checkPlayer(clientSentence, inFromClient, outToClient);
		}
	}
	
	public static void checkPlayer(String action, BufferedReader br, DataOutputStream dos){
		String[] args = action.split(";");
		if(args[0].equals("ADDPLAYER")){
			Player player = new Player(args[1], br, dos, board);
			players.add(player);
			IngoingServer in = new IngoingServer(player, players);
//			OutgoingServer out = new OutgoingServer(player);
			String toClient ="";
			for (Player p: players){
				toClient += p.getName()+";"+p.getXpos()+";"+p.getYpos()+";"+p.getPoint()+";"
			}
			dos.writeBytes();
		}
		else if(args[0].equals("MOVEPLAYER")){
			for(Player p : players){
				if(p.getName().equals(args[0])){
					
				}
			}
		}
		else if(args[0].equals("REMOVE")){
			
		}
	}
}
