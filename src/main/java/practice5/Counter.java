package practice5;

public class Counter implements Runnable {
	private int c1;
	private int c2;

	Counter() {
		this.c1 = 0;
		this.c2 = 0;
	}

	@Override
	public void run() {
		System.out.println(getC1() == getC2());
		incrementC1();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		incrementC2();

	}

	 private void incrementC1() {
		this.c1++;
	}

	 private void incrementC2() {
		this.c2++;
	}

	 private int getC1() {
		return c1;
	}

	 private int getC2() {
		return c2;
	}
}
