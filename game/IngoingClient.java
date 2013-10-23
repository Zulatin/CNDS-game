package game;

import java.io.BufferedReader;
import java.io.IOException;

public class IngoingClient extends Thread {
	private BufferedReader inFromServer;
	private gameplayer game;
	
	public IngoingClient(BufferedReader br, gameplayer game){
		this.inFromServer = br;
		this.game = game;
	}

	public void run(){
		boolean connected = true;
		while (connected){
			try {
				String sentence = inFromServer.readLine();
				String[] boardUpdate = sentence.split(" ");
				for (int i=1; i<Integer.parseInt(boardUpdate[0])*5; i+=5){
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}