package practice5;

public class Part3 {
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread t1 = new Thread(counter);
		Thread t2 = new Thread(counter);

		t1.start();
		t2.start();
	}
}
