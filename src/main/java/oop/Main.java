package oop;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to Battleship!\n\nIntroduction and rules:\n\nYour ships are marked with the letter 'S'.\n" +
				"Hit ships are marked with '@'.\nMisses and known empty squares are marked with 'X'.\n" +
				"Unknown squares are marked with '_'.\n\nShips can be placed and shot at by typing the square's coordinates like 'a3' or 'B10'.\n" +
				"In the case of a hit you get a second shot.\nThe computer fires randomly.\nWho goes first is chosen randomly.\n\nGood luck.");
		Battleship.begin();
	}
}