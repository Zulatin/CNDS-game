package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyClass implements KeyListener
{
	private gameplayer gameplayer;
	private Player player;

	public KeyClass(gameplayer gameplayer, Player player) {
		this.gameplayer = gameplayer;
		this.player = player;
	}

	public void keyPressed(KeyEvent theKeyEvent) {
		String type = null;
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_UP)
		{
			type = "up";
		}
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_DOWN)
		{
			type = "down";
		}
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_LEFT)
		{
			type = "left";
		}
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			type = "right";
		}

		// Press
		gameplayer.PlayerMoved(type, player);
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}