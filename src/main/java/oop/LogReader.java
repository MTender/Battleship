package oop;

import java.io.*;

public class LogReader implements AutoCloseable {
	private final DataInputStream dis;

	public LogReader(String fileName) throws FileNotFoundException {
		this.dis = new DataInputStream(new FileInputStream(fileName));
	}

	public Move readMove() throws IOException {
		return new Move(dis.readBoolean(), dis.readInt(), dis.readInt());
	}

	@Override
	public void close() {
		try {
			dis.close();
		} catch (IOException e) {
			Logger.setLogged(false);
		}
	}
}
