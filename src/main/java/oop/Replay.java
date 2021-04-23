package oop;

import javafx.scene.Scene;

import java.io.IOException;

public class Replay {
	public static void startReplay() {
		try {
			try (LogReader logReader = new LogReader(Logger.getLogFileName())) {
				Scene scene = Interface.createInterface(true);
				Main.getStage().setScene(scene);
				Move move = logReader.readMove();
				// add delay
				while (move != null) {
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
					move = logReader.readMove();
				}
			}
		} catch (IOException e) {
			Logger.setLogged(false);
			GameOver.popUp("Replay failed");
			return;
		}
		GameOver.popUp("Replay complete");
	}
}
