package game;

import java.util.ArrayList;

public class gameplayer
{
//	Screen screen;
	ArrayList<Player> players;
	ScoreList scoreList;
	private String wall = "w";
	private String[][] level =
		{
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w", "e", "e", "w", "w", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w" },
		};

//	public void setScreen(Screen screen)
//	{
//		this.screen = screen;
//	}

	public gameplayer(ScoreList scoreList, ArrayList<Player> players)
	{
		this.scoreList = scoreList;
		this.players = players;
	}
	
	public String[][] getLevel()
	{
		return level;
	}

//	public void PlayerMoved(String direction, Player player)
//	{
//		player.direction = direction;
//		int x = player.getXpos(), y = player.getYpos();
//
//		if (direction.equals("right")) {
//			x = player.getXpos() + 1;
//		}
//		if (direction.equals("left")) {
//			x = player.getXpos() - 1;
//		}
//		if (direction.equals("up")) {
//			y = player.getYpos() - 1;
//		}
//		if (direction.equals("down")) {
//			y = player.getYpos() + 1;
//		}
//
//		if (level[x][y].equals(wall))
//		{
//
//			// Take a point from player
//			player.subOnePoint();
//			// Move player on the board
//			scoreList.updateScoreOnScreenAll();
//
//		} else {
//
//			// Give player a point
//			player.addOnePoint();
//
//			// Update scorelist
//			scoreList.updateScoreOnScreenAll();
//			// Move player on the board
//			screen.movePlayerOnScreen(x, y, player.getDirection(), player);
//
//			// Set players X Y on player object
//			player.setXpos(x);
//			player.setYpos(y);
//
//		}
//	}
}