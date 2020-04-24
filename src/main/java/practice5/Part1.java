package practice5;

public class Part1 {

    public static void main(String[] argv) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Thread2());
        thread2.start();
    }


}

class Thread1 extends Thread {

    public void run() {
        for (int j = 0; j < 4; j++) {
            try {
                System.out.println(Thread1.currentThread().getName());
                Thread1.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Thread2 implements Runnable {

    public void run() {
        for (int j = 0; j < 4; j++) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread1.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
