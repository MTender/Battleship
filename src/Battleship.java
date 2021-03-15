import java.util.Scanner;

public class Battleship {
	public static void begin() {
		String[][] myBoard;
		Ship[] ships = new Ship[10];

		Scanner input = new Scanner(System.in);
		all:
		while (true) {
			System.out.println("Enter 'auto' for automatic or 'manual' for manual board creation: ");
			String generation = input.nextLine();

			if (generation.equals("auto")) {
				while (true) {
					myBoard = Game.createRandomBoard();
					Game.printBoard(myBoard);
					System.out.println("Are you happy with this board?");

					while (true) {
						System.out.println("Enter 'yes' to continue or 'no' to regenerate your board: ");
						String done = input.nextLine();

						if (done.equals("yes")) {
							break all;
						} else if (done.equals("no")) {
							break;
						} else {
							System.out.println("Could not understand input.");
						}
					}
				}
			} else if (generation.equals("manual")) {
				int index = 0;
				String location;
				boolean direction;
				myBoard = Game.createEmptyBoard();

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < i + 1; j++) {
						while (true) {
							Game.printBoard(myBoard);
							System.out.println("Place your ship. Ship length: " + (4 - i));
							System.out.println("Enter starting location: ");
							location = input.nextLine();
							if (Game.inputNotValid(location)) {
								System.out.println("Could not understand input, please re-enter starting location.");
								continue;
							}
							if (i == 3) {
								direction = true;
							} else {
								direction = Game.askDirection(input);
							}
							ships[index] = new Ship(4 - i, location, direction);
							if (ships[index].notInBounds()) {
								System.out.println("Ship out of bounds. Please choose a different location and/or direction.");
								continue;
							}
							if (ships[index].doesCollide(myBoard)) {
								System.out.println("Ship collides with another ship. Please choose a different location and/or direction.");
								continue;
							}
							ships[index].placeShip(myBoard);
							index++;
							break;
						}
					}
				}
				break;
			} else {
				System.out.println("Could not understand input, please re-enter board creation method.");
			}
		}

		Game.printBoard(myBoard);
		String[][] botBoard = Game.createRandomBoard();
		Game.printBoard(botBoard);
	}
}
