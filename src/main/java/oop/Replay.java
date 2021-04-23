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
				while (move != null) {
					if (move.isPlayer()) {
						Game.fire(Computer.getSelfBoard(), Player.getGameBoard(), Computer.getShips(), move.getX(), move.getY(), Interface.getPlayerGameBoard());
					} else {
						Game.fire(Player.getSelfBoard(), Computer.getGameBoard(), Player.getShips(), move.getX(), move.getY(), Interface.getBotGameBoard());
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
