public class Player {
	private static final Ship[] ships = new Ship[10];
	private static String[][] selfBoard;
	private static final String[][] gameBoard = Game.createEmptyBoard();

	public static boolean fire() {
		boolean hit;
		do {
			int[] xy = Game.decipherLocation(Game.playerChoice());
			assert xy != null;
			int x = xy[0];
			int y = xy[1];

			hit = Game.fire(Computer.getSelfBoard(), gameBoard, Computer.getShips(), x, y);
			if (Game.gameOver(Computer.getShips())) return true;
		} while (hit);

		return false;
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

	public static void setSelfBoard(String[][] selfBoard) {
		Player.selfBoard = selfBoard;
	}
}
