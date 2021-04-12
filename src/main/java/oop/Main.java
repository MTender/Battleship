public class Main {
	public static void main(String[] args) {
		System.out.println("""
				Welcome to Battleship!
								
				Introduction and rules:
								
				Your ships are marked with the letter 'S'.
				Hit ships are marked with '@'.
				Misses and known empty squares are marked with 'X'.
				Unknown squares are marked with '_'.
								
				Ships can be placed and shot at by typing the square's coordinates like 'a3' or 'B10'.
				In the case of a hit you get a second shot.
				The computer fires randomly.
				Who goes first is chosen randomly.
								
				Good luck.""");
		Battleship.begin();
	}
}
