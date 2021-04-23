package oop;

import javafx.scene.control.Button;

import java.util.Random;

public class Game {
	public static String[][] createEmptyBoard() {
		String[][] board = new String[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = "_";
			}
		}
		return board;
	}

	public static String[][] createRandomBoard(Ship[] ships) {
		String[][] board = Game.createEmptyBoard();

		Random random = new Random();
		int index = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i + 1; j++) {
				while (true) {
					ships[index] = new Ship(4 - i, random.nextInt(10), random.nextInt(10), random.nextBoolean());
					if (ships[index].notInBounds()) {
						continue;
					}
					if (ships[index].doesCollide(board)) {
						continue;
					}
					ships[index].placeShip(board);
					index++;
					break;
				}
			}
		}

		return board;
	}

	private static void markShip(String[][] board, Ship ship, int x, int y, Board displayBoard) {
		ship.hitShip(x, y);
		if (ship.hasSunk()) ship.sinkProtocol(board, displayBoard);
	}

	private static Ship findShip(Ship[] ships, int x, int y) {
		for (Ship ship : ships) {
			int shipX = ship.getX();
			int shipY = ship.getY();
			boolean shipDir = ship.isDirection();

			if (x == shipX && y == shipY) {
				return ship;
			} else if (x == shipX && !shipDir) {
				if (shipY < y && y < shipY + ship.getLength()) {
					return ship;
				}
			} else if (y == shipY && shipDir) {
				if (shipX < x && x < shipX + ship.getLength()) {
					return ship;
				}
			}
		}
		throw new RuntimeException("Ship not found.");
	}

	public static boolean fire(String[][] opponentBoard, String[][] game, Ship[] opponentShips, int x, int y, Board displayBoard) {
		if (opponentBoard[y][x].equals("S")) {
			game[y][x] = "@";
			Ship ship = findShip(opponentShips, x, y);
			markShip(game, ship, x, y, displayBoard);
			return true;
		} else {
			game[y][x] = "X";
			return false;
		}
	}

	public static boolean gameOver(Ship[] ships) {
		for (Ship ship : ships) {
			if (!ship.isSunken()) return false;
		}
		return true;
	}

	public static void displayBoard(String[][] board, Board whereToDisplay) {
		Button[][] buttons = whereToDisplay.getButtons();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j].equals("S")) {
					buttons[i][j].setStyle("-fx-background-color: black; -fx-border-color: black");
				} else {
					buttons[i][j].setStyle("-fx-background-color: snow; -fx-border-color: lightgrey");
				}
			}
		}
	}
}
