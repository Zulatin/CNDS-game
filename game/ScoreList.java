package game;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoreList extends JFrame
{
	ArrayList<Player> players;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();

	public ScoreList(ArrayList<Player> players)
	{
		super("Bubble Shooter");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(900, 50);
		this.setSize(175, 700);
		this.setResizable(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.setVisible(true);
		this.players = players;
		this.draw();
		this.setAlwaysOnTop(true);
	}

	public void setPlayers(ArrayList<Player> players)
	{
		this.players = players;
	}

	public void draw()
	{
		for (int j = 0; j < this.players.size(); j++)
		{
			JLabel l = new JLabel(this.players.get(j).toString());
			l.setSize(50, 200);
			this.add(l);
			this.labels.add(l);

		}
	}

	public void updateScoreOnScreenAll()
	{
		if (this.players.size() > this.labels.size())
		{
			// New players
			for (int j = this.labels.size(); j < this.players.size(); j++)
			{
				JLabel l = new JLabel(this.players.get(j).toString());
				l.setSize(50, 200);
				this.add(l);
				this.labels.add(l);
			}
		}
		if (this.players.size() < this.labels.size())
		{
			// Players left game
			for (int j = this.labels.size(); j > this.players.size(); j--)
			{
				JLabel lbl = this.labels.remove(j - 1);
				this.remove(lbl);
				this.repaint();
			}
		}
		for (int j = 0; j < this.players.size(); j++)
		{
			this.labels.get((j)).setText(this.players.get(j).toString());
		}
	}
}