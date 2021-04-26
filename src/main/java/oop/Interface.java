package oop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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

	public static Scene createInterface(boolean isReplay) {
		GridPane gp = new GridPane();
		HBox base = new HBox();
		base.setSpacing(60);
		gp.setAlignment(Pos.CENTER);
		GridPane.setMargin(base, new Insets(10));

		Scene scene = new Scene(gp, 920, 790, Color.WHITE);

		VBox left = new VBox();
		HBox.setMargin(left, new Insets(0, 0, 0, 5));
		left.setSpacing(40);

		VBox upperLeft = new VBox();
		upperLeft.setSpacing(10);

		Text upperLeftTitle = new Text("This is what the bot is seeing");
		upperLeftTitle.setFont(Font.font(20));
		VBox.setMargin(upperLeftTitle, new Insets(0, 0, 0, 10));

		botGameBoard = new Board(30, scene);
		upperLeft.getChildren().addAll(upperLeftTitle, botGameBoard.getBoard());

		VBox lowerLeft = new VBox();
		lowerLeft.setSpacing(10);

		Text lowerLeftTitle = new Text("These are your ships");
		lowerLeftTitle.setFont(Font.font(20));
		VBox.setMargin(lowerLeftTitle, new Insets(0, 0, 0, 10));

		playerBoard = new Board(30, scene);
		lowerLeft.getChildren().addAll(lowerLeftTitle, playerBoard.getBoard());

		left.getChildren().addAll(upperLeft, lowerLeft);

		VBox right = new VBox();
		right.setSpacing(40);
		BorderPane.setMargin(right, new Insets(10));

		base.getChildren().addAll(left, right);
		gp.getChildren().add(base);

		VBox upperRight = new VBox();
		upperRight.setSpacing(10);
		VBox lowerRight = new VBox();
		lowerRight.setSpacing(10);

		Text upperRightTitle = new Text();
		upperRightTitle.setFont(Font.font(20));
		Text lowerRightTitle = new Text();
		lowerRightTitle.setFont(Font.font(20));

		HBox urtHolder = new HBox();
		urtHolder.setAlignment(Pos.BASELINE_RIGHT);
		HBox lrtHolder = new HBox();
		lrtHolder.setAlignment(Pos.BASELINE_RIGHT);

		if (isReplay) {
			upperRightTitle.setText("This is what you saw");
			urtHolder.getChildren().add(upperRightTitle);
			playerGameBoard = new Board(30, scene);
			upperRight.getChildren().addAll(urtHolder, playerGameBoard.getBoard());

			lowerRightTitle.setText("These are the computer's ships");
			lrtHolder.getChildren().add(lowerRightTitle);
			Board botBoard = new Board(30, scene);
			lowerRight.getChildren().addAll(lrtHolder, botBoard.getBoard());

			right.getChildren().addAll(upperRight, lowerRight);

			Game.displayBoard(Player.getSelfBoard(), playerBoard);
			Game.displayBoard(Computer.getSelfBoard(), botBoard);
		} else {
			upperRightTitle.setText("Click on these squares to shoot");
			urtHolder.getChildren().add(upperRightTitle);
			playerGameBoard = new Board(40, scene);
			upperRight.getChildren().addAll(urtHolder, playerGameBoard.getBoard());

			lowerRightTitle.setText("Instructions");
			lrtHolder.getChildren().add(lowerRightTitle);

			HBox info = new HBox();
			info.setSpacing(20);
			info.setAlignment(Pos.TOP_RIGHT);

			VBox controls = new VBox();
			Button[] buttons = new Button[]{new Button("Randomize"), new Button("Confirm")};
			for (Button button : buttons) {
				button.setPrefSize(160, 40);
				button.setFont(Font.font(24));
			}
			controls.getChildren().addAll(buttons);

			HBox textHolder = new HBox();

			Text text = new Text("This is a classic game of Battleship.\n\n" +
					"Start the game by randomizing your own ship locations and then confirming.\n" +
					"Shoot your enemy's ships on the above board.\nIn the case of a hit the shooter gets a second shot.");
			text.setWrappingWidth(280);
			text.setFont(Font.font(18));
			text.setTextAlignment(TextAlignment.RIGHT);

			textHolder.getChildren().addAll(text);

			info.getChildren().addAll(controls, textHolder);

			lowerRight.getChildren().addAll(lrtHolder, info);

			right.getChildren().addAll(upperRight, lowerRight);

			enableButtons(buttons);
		}

		return scene;
	}

	public static void enableButtons(Button[] buttons) {
		buttons[1].setVisible(false);

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
			playerGameBoard.setClickable(true);
		});
	}
}
