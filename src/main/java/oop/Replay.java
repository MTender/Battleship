package oop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.io.IOException;

public class Replay {
	private static Timeline timeline;

	public static void startReplay() {
		try {
			LogReader logReader = new LogReader(Logger.getLogFileName());
			Scene scene = Interface.createInterface(true);
			Main.getStage().setScene(scene);
			Game.resetShips();

			timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), actionEvent -> {
				Move move;
				try {
					move = logReader.readMove();
					if (move == null) {
						timeline.stop();
						logReader.close();
						GameOver.popUp("Replay complete");
						return;
					}
					int x = move.getX();
					int y = move.getY();
					if (move.isPlayer()) {
						if (Game.fire(Computer.getSelfBoard(), Player.getGameBoard(), Computer.getShips(), x, y, Interface.getPlayerGameBoard())) {
							Interface.getPlayerGameBoard().getButtons()[y][x].setStyle("-fx-background-color: black; -fx-border-color: black");
						} else {
							Interface.getPlayerGameBoard().getButtons()[y][x].setText("X");
						}
					} else {
						if (Game.fire(Player.getSelfBoard(), Computer.getGameBoard(), Player.getShips(), x, y, Interface.getBotGameBoard())) {
							Interface.getBotGameBoard().getButtons()[y][x].setStyle("-fx-background-color: black; -fx-border-color: black");
						} else {
							Interface.getBotGameBoard().getButtons()[y][x].setText("X");
						}
					}
				} catch (IOException e) {
					timeline.stop();
					logReader.close();
					Logger.setLogged(false);
					GameOver.popUp("Replay failed");
				}
			}));

			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		} catch (IOException e) {
			Logger.setLogged(false);
			GameOver.popUp("Replay failed");
		}
	}
}
