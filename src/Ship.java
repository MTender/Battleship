public class Ship {
	private final int length;
	private int[] hits;
	private boolean sunken;
	private int x;
	private int y;
	private final boolean direction;

	public Ship(int length, String location, boolean direction) {
		this.length = length;
		hits = new int[length];
		for (int i = 0; i < length; i++) {
			hits[i] = 1;
		}
		sunken = false;
		decipherLocation(location);
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
			if (x != 0) {
				if (y != 0) board[y - 1][x - 1] = "*";
				board[y][x - 1] = "*";
				if (y != 9) board[y + 1][x - 1] = "*";
			}
			for (int i = 0; i < length; i++) {
				if (y != 0) board[y - 1][x + i] = "*";
				board[y][x + i] = "S";
				if (y != 9) board[y + 1][x + i] = "*";
			}
			if (x + length <= 9) {
				if (y != 0) board[y - 1][x + length] = "*";
				board[y][x + length] = "*";
				if (y != 9) board[y + 1][x + length] = "*";
			}
		} else {
			if (y != 0) {
				if (x != 0) board[y - 1][x - 1] = "*";
				board[y - 1][x] = "*";
				if (x != 9) board[y - 1][x + 1] = "*";
			}
			for (int i = 0; i < length; i++) {
				if (x != 0) board[y + i][x - 1] = "*";
				board[y + i][x] = "S";
				if (x != 9) board[y + i][x + 1] = "*";
			}
			if (y + length <= 9) {
				if (x != 0) board[y + length][x - 1] = "*";
				board[y + length][x] = "*";
				if (x != 9) board[y + length][x + 1] = "*";
			}
		}
	}

	public boolean hasSunken() {
		for (int hit : hits) {
			if (hit == 1) return false;
		}
		return true;
	}

	public void decipherLocation(String sLocation) {
		String[] parts = sLocation.split("");
		x = (int) Character.toLowerCase(parts[0].charAt(0)) - 97;
		try {
			if (parts.length == 2) {
				y = Integer.parseInt(parts[1]) - 1;
			} else {
				y = Integer.parseInt(parts[1] + parts[2]) - 1;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("This shouldn't happen!");
		}
	}
}
