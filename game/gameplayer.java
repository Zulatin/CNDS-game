package game;

import java.util.ArrayList;

public class gameplayer
{
	private String wall = "w";
	ArrayList<Player> players;

	Player me;
	ScoreList scoreList;
	Screen screen;

	// level is defined column by column, the first row is the first column on the screen.
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

	public gameplayer(Player me, ScoreList scoreList, ArrayList<Player> players)
	{
		this.me = me;
		this.scoreList = scoreList;
		this.players = players;

		// Start new screen
		screen = new Screen(level, me.getXpos(), me.getYpos());
		// Set screen to be visible
		screen.setVisible(true);

		// Add key listener - on keyClass
		screen.addKeyListener(new KeyClass(this));
	}

	public void PlayerMoved(String direction)
	{
		me.direction = direction;
		int x = me.getXpos(), y = me.getYpos();

		if (direction.equals("right")) {
			x = me.getXpos() + 1;
		}
		if (direction.equals("left")) {
			x = me.getXpos() - 1;
		}
		if (direction.equals("up")) {
			y = me.getYpos() - 1;
		}
		if (direction.equals("down")) {
			y = me.getYpos() + 1;
		}

		if (level[x][y].equals(wall))
		{

			// Take a point from player
			me.subOnePoint();
			// Move player on the board
			scoreList.updateScoreOnScreenAll();

		} else {

			// Give player a point
			me.addOnePoint();

			// Update scorelist
			scoreList.updateScoreOnScreenAll();
			// Move player on the board
			screen.movePlayerOnScreen(me.getXpos(), me.getYpos(), x, y, me.getDirection());

			// Set players X Y on player object
			me.setXpos(x);
			me.setYpos(y);

		}
	}
}