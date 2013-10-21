package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyClass implements KeyListener
{
	private gameplayer gameplayer;

	public KeyClass(gameplayer gameplayer) {
		this.gameplayer = gameplayer;
	}

	public void keyPressed(KeyEvent theKeyEvent) {
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_UP)
		{
			gameplayer.PlayerMoved("up");
		}
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_DOWN)
		{
			gameplayer.PlayerMoved("down");
		}
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_LEFT)
		{
			gameplayer.PlayerMoved("left");
		}
		if (theKeyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			gameplayer.PlayerMoved("right");
		}
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}