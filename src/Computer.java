import java.util.Random;

public class Computer {
	private static final Ship[] ships = new Ship[10];
	private static final String[][] selfBoard = Game.createRandomBoard(ships);
	private static final String[][] gameBoard = Game.createEmptyBoard();

	public static boolean fire() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(10);
			y = random.nextInt(10);
		} while (gameBoard[y][x].equals("X") || gameBoard[y][x].equals("@"));

		Game.fire(Player.getSelfBoard(), gameBoard, Player.getShips(), x, y);

		return Game.gameOver(Player.getShips());
	}

	public static Ship[] getShips() {
		return ships;
	}

	public static String[][] getSelfBoard() {
		return selfBoard;
	}

	public static String[][] getGameBoard() {
		return gameBoard;
	}
}
