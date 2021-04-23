package oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
	private static Stage stage;


	@Override
	public void start(Stage stage) {
		Main.stage = stage;
		startNewGame();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void startNewGame() {
		initiateClassVariables();
		Scene scene = Interface.createInterface(false);
		try {
			Logger.setLogWriter(new LogWriter(Logger.getLogFileName()));
		} catch (FileNotFoundException e) {
			Logger.setLogged(false);
		}
		stage.setTitle("Battleship");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void initiateClassVariables() {
		Logger.setLogged(true);
		Player.initiate();
		Computer.initiate();
	}

	public static Stage getStage() {
		return stage;
	}
}
