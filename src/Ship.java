public class Ship {
	private final int length;
	private final int[] hits;
	private boolean sunken;
	private final int x;
	private final int y;
	private final boolean direction;

	public Ship(int length, String location, boolean direction) {
		this.length = length;
		hits = new int[length];
		for (int i = 0; i < length; i++) {
			hits[i] = 1;
		}
		sunken = false;
		int[] xy = Game.decipherLocation(location);
		assert xy != null;
		x = xy[0];
		y = xy[1];
		this.direction = direction;
	}

	public Ship(int length, int x, int y, boolean direction) {
		this.length = length;
		hits = new int[length];
		for (int i = 0; i < length; i++) {
			hits[i] = 1;
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
		surroundShip(board, "*");
	}

	private void surroundShip(String[][] board, String letter) {
		if (direction) {
			if (x != 0) {
				if (y != 0) board[y - 1][x - 1] = letter;
				board[y][x - 1] = letter;
				if (y != 9) board[y + 1][x - 1] = letter;
			}
			for (int i = 0; i < length; i++) {
				if (y != 0) board[y - 1][x + i] = letter;
				if (y != 9) board[y + 1][x + i] = letter;
			}
			if (x + length <= 9) {
				if (y != 0) board[y - 1][x + length] = letter;
				board[y][x + length] = letter;
				if (y != 9) board[y + 1][x + length] = letter;
			}
		} else {
			if (y != 0) {
				if (x != 0) board[y - 1][x - 1] = letter;
				board[y - 1][x] = letter;
				if (x != 9) board[y - 1][x + 1] = letter;
			}
			for (int i = 0; i < length; i++) {
				if (x != 0) board[y + i][x - 1] = letter;
				if (x != 9) board[y + i][x + 1] = letter;
			}
			if (y + length <= 9) {
				if (x != 0) board[y + length][x - 1] = letter;
				board[y + length][x] = letter;
				if (x != 9) board[y + length][x + 1] = letter;
			}
		}
	}

	public boolean hasSunk() {
		for (int hit : hits) {
			if (hit == 1) return false;
		}
		sunken = true;
		return true;
	}

	public void hitShip(int fX, int fY) {
		int pos = (fX == x) ? Math.abs(y - fY) : Math.abs(x - fX);
		hits[pos] = 0;
	}

	public void sinkProtocol(String[][] board) {
		System.out.println("The ship has been sunk.");
		surroundShip(board, "X");
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
