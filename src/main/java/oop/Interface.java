package oop;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Interface {
	private static Board botGameBoard;
	private static Board playerBoard;
	private static Board playerGameBoard;

	public static Board getPlayerGameBoard() {
		return playerGameBoard;
	}

	public static Board getBotGameBoard() {
		return botGameBoard;
	}

	public static Scene createInterface() {
		BorderPane bp = new BorderPane();

		VBox left = new VBox();
		botGameBoard = new Board(30, false);
		playerBoard = new Board(30, false);

		left.getChildren().addAll(botGameBoard.getBoard(), playerBoard.getBoard());
		left.setSpacing(40);

		VBox right = new VBox();
		playerGameBoard = new Board(40, false);

		HBox info = new HBox();

		VBox controls = new VBox();
		Button[] buttons = new Button[]{new Button("Randomize"), new Button("Confirm"), new Button("Rotate")};
		for (Button button : buttons) {
			button.setPrefSize(200, 50);
			button.setFont(Font.font(30));
		}
		controls.getChildren().addAll(buttons);

		VBox text = new VBox();
		Text upper = new Text();
		Text lower = new Text();
		text.getChildren().addAll(upper, lower);

		info.getChildren().addAll(controls, text);

		right.getChildren().addAll(playerGameBoard.getBoard(), info);
		right.setSpacing(40);

		bp.setLeft(left);
		bp.setRight(right);

		enableButtons(buttons);

		return new Scene(bp, 900, 700, Color.WHITE);
	}

	public static void enableButtons(Button[] buttons) {
		buttons[1].setVisible(false);
		buttons[2].setVisible(false);

		buttons[0].setOnMouseClicked(randomize -> {
			String[][] myBoard = Game.createRandomBoard(Player.getShips());
			Game.displayBoard(myBoard, playerBoard);
			buttons[1].setVisible(true);
			confirmBoard(buttons, myBoard);
		});
	}

	private static void confirmBoard(Button[] buttons, String[][] board) {
		buttons[1].setOnMouseClicked(confirm -> {
			Player.setSelfBoard(board);
			for (Button button : buttons) {
				button.setVisible(false);
			}
			startGameplay();
		});
	}

	private static void startGameplay() {
		playerGameBoard.setClickable(true);
	}
}
