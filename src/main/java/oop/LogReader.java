package oop;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LogReader implements AutoCloseable {
	private final BufferedReader br;

	public LogReader(String fileName) throws FileNotFoundException {
		this.br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
	}

	public Move readMove() throws IOException {
		String line = br.readLine();
		if (line == null) return null;
		String[] info = line.split(",");
		return new Move(line.charAt(0) == '1', Integer.parseInt(info[1]), Integer.parseInt(info[2]));
	}

	@Override
	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			Logger.setLogged(false);
		}
	}
}
