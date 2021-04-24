package oop;

public class Move {
	private final boolean isPlayer;
	private final int x;
	private final int y;

	public Move(boolean isPlayer, int x, int y) {
		this.isPlayer = isPlayer;
		this.x = x;
		this.y = y;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
