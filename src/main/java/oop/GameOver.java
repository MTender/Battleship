package oop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOver {
	public static void popUp(String message) {
		Logger.getLogWriter().close();

		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);

		StackPane sp = new StackPane();
		Scene popupScene = new Scene(sp, 300, 150);

		Label victory = new Label(message);

		Text question;
		if (Logger.isLogged()) {
			question = new Text("Do you wish to play again, watch a replay of the game you just played, or exit?\n(p / r / e)");
		} else {
			question = new Text("Do you wish to play again or exit?\n(p / e)");
		}

		sp.getChildren().addAll(victory, question);
		StackPane.setAlignment(victory, Pos.TOP_CENTER);
		StackPane.setAlignment(question, Pos.BOTTOM_CENTER);
		StackPane.setMargin(question, new Insets(0, 0, 15, 0));

		popupScene.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (code == KeyCode.P) {
				popup.hide();
				Main.startNewGame();
			} else if (code == KeyCode.R && Logger.isLogged()) {
				popup.hide();
				Replay.startReplay();
			} else if (code == KeyCode.E) {
				System.exit(0);
			}
		});

		victory.setFont(Font.font(32));
		question.setFont(Font.font(16));
		question.setWrappingWidth(220);
		question.setTextAlignment(TextAlignment.CENTER);


		popup.setTitle("Game Over");
		popup.setScene(popupScene);
		popup.setResizable(false);
		popup.show();
	}
}
