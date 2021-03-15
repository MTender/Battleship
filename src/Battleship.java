import java.util.Scanner;

public class Battleship {
	public static void begin() {
		String[][] myBoard = Game.createEmptyBoard();

		Game.printBoard(myBoard);

		Ship[] ships = Game.createShipArray();

		Scanner input = new Scanner(System.in);
		int index = 0;
		String location;
		boolean direction;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i + 1; j++) {
				while (true) {
					System.out.println("Place your ship. (Ship length: " + (4 - i) + ")");
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
					Game.printBoard(myBoard);
					index++;
					break;
				}
			}
		}

		Game.printBoard(Bot.createRandomBoard());
	}
}
