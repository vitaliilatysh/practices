package practice5;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {

	private static final int DELAY = 3000;
	private static final InputStream STD_IN = System.in;

	public static void main(String[] args) throws IOException, InterruptedException {
		// create the mock input
		MyInputStream mockIn = new MyInputStream(DELAY);
		// set the mock input
		System.setIn(mockIn);
		// start the thread for delayed output
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Spam.main(null);
				} catch (Exception e) {
					// nothing to do
				}
			}
		};
		t.start();
		t.join();
		// restore the standard input
		System.setIn(STD_IN);
		
	}
}

class MyInputStream extends InputStream {

	long millis;
	boolean isFirstCall = true;
	int current;
	byte[] bytes = System.lineSeparator().getBytes();
	
	public MyInputStream(long millis) {
		super();
		this.millis = millis;
	}

	@Override
	public int read() throws IOException {
		try {
			if (isFirstCall)
				Thread.sleep(millis);
			isFirstCall = false;
		} catch (InterruptedException e) {
			return -1;
		}
		if (current < bytes.length)
			return bytes[current++];
		return -1;
	}
	
}
