package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class game
{
	public static ArrayList<Player> players;
	public static Player me, fup;
	static Screen screen;

	public static void main(String[] args) throws Exception
	{
		System.out.println("Indtast dit spillernavn");
		BufferedReader bName = new BufferedReader(new InputStreamReader(System.in));

		// Make player list
		players = new ArrayList<Player>();

		// Create you
		me = new Player(bName.readLine());
		fup = new Player("FUP");

		// Add players to play list
		players.add(me);
		players.add(fup);
		fup.setYpos(10);

		// Create a scorelist
		ScoreList scoreList = new ScoreList(players);
		scoreList.setVisible(true);


		// Gameplayet
		gameplayer gp = new gameplayer(scoreList, players);


		// Start new screen
		screen = new Screen(gp.getLevel());
		// Set screen to be visible
		screen.setVisible(true);

		gp.setScreen(screen);

		// Add key listener - on keyClass
		screen.addKeyListener(new KeyClass(gp, me));

		// Draw player
		screen.drawPlayer(me);
		screen.drawPlayer(fup);
	}
}