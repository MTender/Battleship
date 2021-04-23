package oop;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LogWriter {
	private final BufferedWriter bw;

	public LogWriter(String fileName) throws FileNotFoundException {
		this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
	}

	public void writeMove(boolean who, int x, int y) throws IOException {
		bw.append(who ? '1' : '0');
		bw.append(',');
		bw.append(String.valueOf(x));
		bw.append(',');
		bw.append(String.valueOf(y));
		bw.newLine();
		bw.flush();
	}

	public void close() {
		try {
			bw.close();
		} catch (IOException e) {
			Logger.setLogged(false);
		}
	}
}
