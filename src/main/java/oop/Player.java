package oop;

import javafx.scene.control.Button;

public class Player {
	private static final Ship[] ships = new Ship[10];
	private static String[][] selfBoard;
	private static final String[][] gameBoard = Game.createEmptyBoard();

	public static boolean fire(Button square, int x, int y) {
		Interface.getPlayerGameBoard().setClickable(false);
		boolean hit = Game.fire(Computer.getSelfBoard(), gameBoard, Computer.getShips(), x, y, Interface.getPlayerGameBoard());

		if (!hit) {
			square.setText("X");
			if (Computer.fire()) return false;
		} else {
			square.setStyle("-fx-background-color: black; -fx-border-color: black");
			if (Game.gameOver(Computer.getShips())) return true;
		}
		Interface.getPlayerGameBoard().setClickable(true);

		return false;
	}

	public static Ship[] getShips() {
		return ships;
	}

	public static String[][] getSelfBoard() {
		return selfBoard;
	}

	public static void setSelfBoard(String[][] selfBoard) {
		Player.selfBoard = selfBoard;
	}
}
