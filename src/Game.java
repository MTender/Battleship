import java.util.InputMismatchException;
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

	public static Ship[] createShipArray() {
		Ship battleship = new Ship();
		Ship destroyer1 = new Ship();
		Ship destroyer2 = new Ship();
		Ship submarine1 = new Ship();
		Ship submarine2 = new Ship();
		Ship submarine3 = new Ship();
		Ship patrolBoat1 = new Ship();
		Ship patrolBoat2 = new Ship();
		Ship patrolBoat3 = new Ship();
		Ship patrolBoat4 = new Ship();

		return new Ship[]{battleship, destroyer1, destroyer2, submarine1, submarine2, submarine3, patrolBoat1, patrolBoat2, patrolBoat3, patrolBoat4};
	}
}
