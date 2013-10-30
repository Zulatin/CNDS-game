package game;

public class ShootThread extends Thread {

	private String[][] level;
	private Player player;
	private String direction;

	public ShootThread(String[][] level, Player p, Screen s) {
		this.level = level;
		player = p;
		
	}

	public void run() {
		int x = player.getXpos();
		int y = player.getYpos();
		direction = player.getDirection();
		int counter = 0;
		

		if (direction.equals("right")) {
			x++;
			counter++;
			boolean found = false;
			while(!found){
//				if()
			}
		}
		if (direction.equals("up")) {

		}
		if (direction.equals("down")) {

		}
		if (direction.equals("left")) {

		}
	}
}
