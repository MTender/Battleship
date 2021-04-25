package oop;

import java.io.IOException;

public class Logger {
	private static boolean logged;
	private static LogWriter logWriter;
	private static final String logFileName = "battleship-log.csv";

	public static void logMove(boolean who, int x, int y) {
		if (logged) {
			try {
				logWriter.writeMove(who, x, y);
			} catch (IOException e) {
				logged = false;
			}
		}
	}

	public static boolean isLogged() {
		return logged;
	}

	public static void setLogged(boolean logged) {
		Logger.logged = logged;
	}

	public static LogWriter getLogWriter() {
		return logWriter;
	}

	public static void setLogWriter(LogWriter logWriter) {
		Logger.logWriter = logWriter;
	}

	public static String getLogFileName() {
		return logFileName;
	}
}
