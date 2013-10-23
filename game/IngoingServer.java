package game;

import java.io.IOException;
import java.util.ArrayList;

public class IngoingServer extends Thread{

	private Player player;
	private String direction;
	private String wall = "w";
	private ScoreList scoreList;
	private ArrayList<Player> players;

	public IngoingServer(Player player, ArrayList<Player> players, ScoreList scoreList){
		this.player = player;
		this.players = new ArrayList<Player>(players);
	}

	public void run(){
		while(true){
			try {
				direction = player.getInFromClient().readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
			if (level[x][y].equals(wall)){

				// Take a point from player
				player.subOnePoint();
				// Move player on the board
				scoreList.updateScoreOnScreenAll();

			} else {

				// Give player a point
				player.addOnePoint();

				// Update scorelist
				scoreList.updateScoreOnScreenAll();
				
				// Move player on the board
				//screen.movePlayerOnScreen(player.getXpos(), player.getYpos(), x, y, player.getDirection());
				String toClient ="";
				for (Player p: players){
					toClient += p.getName()+";"+p.getXpos()+";"+p.getYpos()+";"+p.getPoint()+";"+p.getDirection()+";";
				}
				player.getOutToClient().writeBytes(toClient);
			}

				// Set players X Y on player object
//				player.setXpos(x);
//				player.setYpos(y);

			}
		}
	}
}
