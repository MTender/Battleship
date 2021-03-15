import java.util.Random;

public class Bot {
	private static String[][] botBoard;

	public static String[][] createRandomBoard() {
		botBoard = Game.createEmptyBoard();

		Ship[] ships = Game.createShipArray();

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
					if (ships[index].doesCollide(botBoard)) {
						continue;
					}
					ships[index].placeShip(botBoard);
					index++;
					break;
				}
			}
		}

		return botBoard;
	}
}
