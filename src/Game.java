import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public static void printBoard(String[][] board) {
		System.out.println("\n    A  B  C  D  E  F  G  H  I  J");
		for (int i = 0; i < 10; i++) {
			if (i != 9) {
				System.out.print(" " + (i + 1) + "  ");
			} else {
				System.out.print(i + 1 + "  ");
			}
			for (int j = 0; j < 10; j++) {
				System.out.print(board[i][j] + "  ");
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

	public static String[][] createRandomBoard() {
		String[][] board = Game.createEmptyBoard();

		Ship[] ships = new Ship[10];

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
}