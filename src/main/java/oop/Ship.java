package oop;

public class Ship {
	private final int length;
	private final boolean[] hits;
	private boolean sunken;
	private final int x;
	private final int y;
	private final boolean direction;

	public Ship(int length, int x, int y, boolean direction) {
		this.length = length;
		hits = new boolean[length];
		for (int i = 0; i < length; i++) {
			hits[i] = false;
		}
		sunken = false;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public boolean notInBounds() {
		if (x < 0 || y < 0) return true;
		if (direction && (x + length - 1 > 9 || y > 9)) return true;
		return !direction && (x > 9 || y + length - 1 > 9);
	}

	public boolean doesCollide(String[][] board) {
		String spot;
		if (direction) {
			for (int i = 0; i < length; i++) {
				spot = board[y][x + i];
				if (spot.equals("S") || spot.equals("*")) return true;
			}
		} else {
			for (int i = 0; i < length; i++) {
				spot = board[y + i][x];
				if (spot.equals("S") || spot.equals("*")) return true;
			}
		}
		return false;
	}

	public void placeShip(String[][] board) {
		if (direction) {
			for (int i = 0; i < length; i++) {
				board[y][x + i] = "S";
			}
		} else {
			for (int i = 0; i < length; i++) {
				board[y + i][x] = "S";
			}
		}
		surroundShip(board, "*", null);
	}

	private void surroundShip(String[][] board, String letter, Board displayBoard) {
		if (direction) {
			if (x != 0) {
				if (y != 0) markLocation(x - 1, y - 1, board, letter, displayBoard);
				markLocation(x - 1, y, board, letter, displayBoard);
				if (y != 9) markLocation(x - 1, y + 1, board, letter, displayBoard);
			}
			for (int i = 0; i < length; i++) {
				if (y != 0) markLocation(x + i, y - 1, board, letter, displayBoard);
				if (y != 9) markLocation(x + i, y + 1, board, letter, displayBoard);
			}
			if (x + length <= 9) {
				if (y != 0) markLocation(x + length, y - 1, board, letter, displayBoard);
				markLocation(x + length, y, board, letter, displayBoard);
				if (y != 9) markLocation(x + length, y + 1, board, letter, displayBoard);
			}
		} else {
			if (y != 0) {
				if (x != 0) markLocation(x - 1, y - 1, board, letter, displayBoard);
				markLocation(x, y - 1, board, letter, displayBoard);
				if (x != 9) markLocation(x + 1, y - 1, board, letter, displayBoard);
			}
			for (int i = 0; i < length; i++) {
				if (x != 0) markLocation(x - 1, y + i, board, letter, displayBoard);
				if (x != 9) markLocation(x + 1, y + i, board, letter, displayBoard);
			}
			if (y + length <= 9) {
				if (x != 0) markLocation(x - 1, y + length, board, letter, displayBoard);
				markLocation(x, y + length, board, letter, displayBoard);
				if (x != 9) markLocation(x + 1, y + length, board, letter, displayBoard);
			}
		}
	}

	private static void markLocation(int x, int y, String[][] board, String letter, Board displayBoard) {
		board[y][x] = letter;
		if (displayBoard != null) displayBoard.getButtons()[y][x].setText("X");
	}

	public boolean hasSunk() {
		for (boolean hit : hits) {
			if (!hit) return false;
		}
		sunken = true;
		return true;
	}

	public void hitShip(int fX, int fY) {
		hits[fX == x ? fY - y : fX - x] = true;
	}

	public void sinkProtocol(String[][] board, Board displayBoard) {
		surroundShip(board, "X", displayBoard);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLength() {
		return length;
	}

	public boolean isSunken() {
		return sunken;
	}
}
