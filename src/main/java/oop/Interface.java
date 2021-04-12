package oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Interface extends Application {

	@Override
	public void start(Stage peaLava) {
		BorderPane bp = new BorderPane();

		VBox left = new VBox();
		Board botGameBoard = new Board(30, false);
		Board playerBoard = new Board(30, false);

		left.getChildren().addAll(botGameBoard.getBoard(), playerBoard.getBoard());
		left.setSpacing(40);

		VBox right = new VBox();
		Board playerGameBoard = new Board(40, true);

		VBox controls = new VBox();
		Button[] buttons = new Button[]{new Button("Randomize"), new Button("Confirm"), new Button("Rotate")};
		for (Button button : buttons) {
			button.setPrefSize(200, 50);
			button.setFont(Font.font(30));
		}
		controls.getChildren().addAll(buttons);

		right.getChildren().addAll(playerGameBoard.getBoard(), controls);
		right.setSpacing(40);

		bp.setLeft(left);
		bp.setRight(right);

		Scene scene = new Scene(bp, 900, 700, Color.WHITE);
		peaLava.setTitle("Battleship");
		peaLava.setResizable(false);
		peaLava.setScene(scene);
		peaLava.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
