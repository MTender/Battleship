import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public static void printBoard(String[][] board) {
		System.out.println("    A  B  C  D  E  F  G  H  I  J");
		for (int i = 0; i < 10; i++) {
			if (i != 9) {
				System.out.print(" " + (i + 1) + "  ");
			} else {
				System.out.print(i + 1 + "  ");
			}
			for (int j = 0; j < 10; j++) {
				String g = board[i][j];
				System.out.print((g.equals("*")) ? "_  " : g + "  ");
			}
			System.out.println();
		}
	}

	public static String[][] createEmptyBoard() {
		String[][] board = new String[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = "_";
			}
		}
		return board;
	}

	public static boolean inputNotValid(String input) {
		if (input.length() != 2 && input.length() != 3) return true;
		String[] parts = input.split("");
		int ascii = Character.toLowerCase(parts[0].charAt(0));
		if (ascii < 97 || ascii > 106) return true;
		try {
			int row;
			if (input.length() == 2) {
				row = Integer.parseInt(parts[1]);
			} else {
				row = Integer.parseInt(parts[1] + parts[2]);
			}
			if (row < 1 || row > 10) return true;
		} catch (NumberFormatException nfe) {
			return true;
		}
		return false;
	}

	public static boolean askDirection(Scanner input) {
		int direction;
		while (true) {
			System.out.println("Enter '1' for horizontal, '0' for vertical: ");
			try {
				direction = input.nextInt();
				if (direction == 0 || direction == 1) {
					break;
				}
			} catch (InputMismatchException ime) {
				input.nextLine();
			}
			System.out.println("Could not understand input, please re-enter direction.");
		}
		input.nextLine();
		return direction != 0;
	}

	public static String[][] createRandomBoard(Ship[] ships) {
		String[][] board = Game.createEmptyBoard();

		Random random = new Random();
		int index = 0;
		boolean direction;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i + 1; j++) {
				while (true) {
					direction = random.nextBoolean();
					ships[index] = new Ship(4 - i, random.nextInt(10), random.nextInt(10), direction);
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

	private static void markShip(String[][] board, Ship ship, int x, int y) {
		assert ship != null;
		ship.hitShip(x, y);
		if (ship.hasSunk()) ship.sinkProtocol(board);
	}

	private static Ship findShip(Ship[] ships, int x, int y) {
		for (Ship ship : ships) {
			int shipX = ship.getX();
			int shipY = ship.getY();

			if (x == shipX && y == shipY) {
				return ship;
			} else if (x == shipX) {
				if (shipY < y && y < shipY + ship.getLength()) {
					return ship;
				}
			} else if (y == shipY) {
				if (shipX < x && x < shipX + ship.getLength()) {
					return ship;
				}
			}
		}
		return null;
	}

	public static boolean fire(String[][] opponentBoard, String[][] game, Ship[] opponentShips, int x, int y) {
		if (opponentBoard[y][x].equals("S")) {
			System.out.println("Hit");
			game[y][x] = "@";
			Ship ship = findShip(opponentShips, x, y);
			assert ship != null;
			markShip(game, ship, x, y);
			return true;
		} else {
			System.out.println("Miss");
			game[y][x] = "X";
			return false;
		}
	}

	public static String playerChoice() {
		Scanner input = new Scanner(System.in);
		String location;
		while (true) {
			Game.printBoard(Player.getGameBoard());
			System.out.println("On which square do you want to fire?");
			System.out.println("Enter location: ");
			location = input.nextLine();
			if (Game.inputNotValid(location)) {
				System.out.println("Could not understand input, please re-enter starting location.");
				continue;
			}
			break;
		}
		return location;
	}

	public static int[] decipherLocation(String sLocation) {
		String[] parts = sLocation.split("");
		int x, y;
		x = (int) Character.toLowerCase(parts[0].charAt(0)) - 97;
		try {
			if (parts.length == 2) {
				y = Integer.parseInt(parts[1]) - 1;
			} else {
				y = Integer.parseInt(parts[1] + parts[2]) - 1;
			}
			return new int[]{x, y};
		} catch (NumberFormatException nfe) {
			System.out.println("This shouldn't happen!");
		}
		return null;
	}

	public static boolean gameOver(Ship[] ships) {
		for (Ship ship : ships) {
			if (!ship.isSunken()) return false;
		}
		return true;
	}
}
