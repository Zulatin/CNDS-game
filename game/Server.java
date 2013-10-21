package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Server {
	
	public static void main(String[] args)throws Exception {
		String clientSentence;
		String sentence;
		ServerSocket welcomeSocket = new ServerSocket(7531);
//		gameplayer board = new gameplayer();
		IngoingServer in = new IngoingServer();
		OutgoingServer out = new OutgoingServer();
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			
		}
	}

}
