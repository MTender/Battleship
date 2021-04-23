package oop;

public class Move {
	private final boolean who;
	private final int x;
	private final int y;
	private final boolean didHit;

	public Move(boolean who, int x, int y, boolean didHit) {
		this.who = who;
		this.x = x;
		this.y = y;
		this.didHit = didHit;
	}

	public boolean isWho() {
		return who;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDidHit() {
		return didHit;
	}
}
