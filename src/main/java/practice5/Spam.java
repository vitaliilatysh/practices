package practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spam {

	public void start() {
		System.out.println("Start");
	}

	public void stop() {
		System.out.println("Stop");
	}

	public static void main(String[] args) throws IOException {
		Spam p = new Spam();
		p.start(); // start all child threads
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in.readLine(); // read all characters with lineSeparator

		p.stop();  // stop all child threads
		in.close();
	}
}
