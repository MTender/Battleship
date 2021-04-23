package oop;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board {
	private final int squareDimentions;
	private VBox board;
	private Button[][] buttons;
	private boolean clickable;

	public Board(int squareDimentions, boolean clickable) {
		this.squareDimentions = squareDimentions;
		this.clickable = clickable;
		generate();
	}

	public VBox getBoard() {
		return board;
	}

	public Button[][] getButtons() {
		return buttons;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public void generate() {
		board = new VBox();

		HBox letters = new HBox();
		letters.getChildren().add(new Rectangle(squareDimentions * 2, squareDimentions, Color.rgb(244, 244, 244)));
		for (int i = 'A'; i <= 'J'; i++) {
			StackPane sp = new StackPane();
			Text letter = new Text(Character.toString(i));
			letter.setFont(Font.font("Consolas", squareDimentions));
			sp.getChildren().addAll(new Rectangle(squareDimentions, squareDimentions, Color.rgb(244, 244, 244)), letter);
			letters.getChildren().add(sp);
		}
		board.getChildren().add(letters);

		buttons = new Button[10][10];

		for (int i = 0; i < 10; i++) {
			HBox row = new HBox();
			StackPane sp = new StackPane();
			Text number = new Text((i != 9 ? " " : "") + (i + 1));
			number.setFont(Font.font("Consolas", squareDimentions));
			sp.getChildren().addAll(new Rectangle(squareDimentions * 2, squareDimentions, Color.rgb(244, 244, 244)), number);
			row.getChildren().add(sp);
			for (int j = 0; j < 10; j++) {
				Button square = new Button();
				square.setPrefSize(squareDimentions, squareDimentions);
				square.setStyle("-fx-background-color: snow; -fx-border-color: lightgrey");
				buttons[i][j] = square;
				int y = i, x = j;
				square.setOnMouseClicked(event -> {
					if (clickable) {
						boolean playerWon = Player.fire(square, x, y);
						if (playerWon) GameOver.popUp("YOU WON!");
						else if (Game.gameOver(Player.getShips())) {
							GameOver.popUp("COMPUTER WINS");
						}
					}
				});
				row.getChildren().add(square);
			}
			board.getChildren().add(row);
		}
	}
}
