package oop;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LogWriter {
	private final DataOutputStream dos;

	public LogWriter(String fileName) throws FileNotFoundException {
		this.dos = new DataOutputStream(new FileOutputStream(fileName));
	}

	public void writeMove(boolean who, int x, int y) throws IOException {
		dos.writeBoolean(who);
		dos.writeInt(x);
		dos.writeInt(y);
	}

	public void close() {
		try {
			dos.close();
		} catch (IOException e) {
			Logger.setLogged(false);
		}
	}
}
