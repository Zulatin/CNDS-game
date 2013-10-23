package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
	

	public static void main(String[] args) throws IOException{
		System.out.println("Input player name");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("192.168.1.1", 7531);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String sentence = inFromUser.readLine();
		outToServer.writeBytes("ADDPLAYER;" + sentence + '\n');
		String response = inFromServer.readLine();
		if (!response.equals("Server full")){
			String[] settings = sentence.split(";");
//			int levelX = Integer.parseInt(settings[0]), levelY = Integer.parseInt(settings[1]);
//			String[][] level = new String[20][20];
			int index = 0;
//			for (int i = 0; i<20; i++){
//				for (int j = 0; j<20; j++){
//					level[i][j] = settings[index];
//					index++;
//				}
//			}
			ArrayList<Player> players = new ArrayList<Player>();
			Player me;
			for (; index<settings.length; index+=5){
				Player p = new Player(settings[index], Integer.parseInt(settings[index+3]));
				p.setXpos(Integer.parseInt(settings[index+1]));
				p.setYpos(Integer.parseInt(settings[index+2]));
				p.setDirection(settings[index+4]);
				players.add(p);
				if (p.getName().equals(inFromUser)) me = p;
			}
			
			// Create a scorelist
			ScoreList scoreList = new ScoreList(players);
			scoreList.setVisible(true);

			// Gameplayet
			gameplayer game = new gameplayer(me, scoreList, players);
			
			
			// IngoingClient needs gameplayer to make changes
			IngoingClient inThread = new IngoingClient(inFromServer, game);
			OutgoingClient outThread = new OutgoingClient();
			
			inThread.start();
			outThread.start();
		}
		else {
			System.out.println("Server is full");
		}
		
		
		clientSocket.close();
	}
	
	
	
	
}
