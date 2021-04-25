package oop;

import java.util.Random;

public class Computer {
	private static Ship[] ships;
	private static String[][] selfBoard;
	private static String[][] gameBoard;

	public static boolean fire() {
		boolean hit;
		Random random = new Random();
		do {
			// currently the bot fires completely randomly but in the future in the case of a hit I'll make it shoot around the hit
			int x, y;
			do {
				x = random.nextInt(10);
				y = random.nextInt(10);
			} while (gameBoard[y][x].equals("X") || gameBoard[y][x].equals("@"));

			hit = Game.fire(Player.getSelfBoard(), gameBoard, Player.getShips(), x, y, Interface.getBotGameBoard());
			Logger.logMove(false, x, y);

			if (!hit) {
				Interface.getBotGameBoard().getButtons()[y][x].setText("X");
			} else {
				Interface.getBotGameBoard().getButtons()[y][x].setStyle("-fx-background-color: black; -fx-border-color: black");
				if (Game.gameOver(Player.getShips())) return true;
			}
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

	public static void initiate() {
		ships = new Ship[10];
		selfBoard = Game.createRandomBoard(ships);
		gameBoard = Game.createEmptyBoard();
	}
}
