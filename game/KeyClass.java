package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class KeyClass implements KeyListener
{
	private DataOutputStream dos;

	public KeyClass(DataOutputStream dos)
	{
		this.dos = dos;
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
		if(type != null)
			try {
				dos.writeBytes("MOVEPLAYER;" + type + '\n');
			} catch (IOException e) {}
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}