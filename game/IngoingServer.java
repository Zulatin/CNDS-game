package game;

import java.io.IOException;
import java.util.ArrayList;

public class IngoingServer extends Thread{

	private Player player;
	private String direction = "left";
	private String wall = "w";
//	private ScoreList scoreList;
//	private ArrayList<Player> players;
	private String command = "";

	public IngoingServer(Player player){
		this.player = player;
//		this.players = new ArrayList<Player>(players);
//		this.scoreList = scoreList;
	}

	public void run()
	{
		while(true)
		{
			System.out.println("Efter");

			try {

				String s = player.getInFromClient().readLine();
				System.out.println(s);
				String[] message = s.split(";");
				direction = message[1];
				command = message[0];
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (command.equals("REMOVEPLAYER")){

			}
			if (command.equals("MOVEPLAYER")){
				if (direction != null){
					int x = player.getXpos(), y = player.getYpos();

					if (direction.equals("right")) {
						x = player.getXpos() + 1;
					}
					if (direction.equals("left")) {
						x = player.getXpos() - 1;
					}
					if (direction.equals("up")) {
						y = player.getYpos() - 1;
					}
					if (direction.equals("down")) {
						y = player.getYpos() + 1;
					}

					String[][] level = player.getBoard().getLevel();
					if (level[x][y].equals(wall))
					{

						// Take a point from player
						player.subOnePoint();
						// Move player on the board
						Server.scoreList.updateScoreOnScreenAll();

					} else {

						player.addOnePoint();
						Server.scoreList.updateScoreOnScreenAll();

						String toClient = "";
						player.setXpos(x);
						player.setYpos(y);
						for (Player p: Server.players)
						{
							toClient += p.getName() + ";" + p.getXpos() + ";" + p.getYpos() + ";" + p.getPoint() + ";" + p.getDirection() + ";";
						}

						try {
							player.getOutToClient().writeBytes(toClient + '\n');
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}
	}
}
