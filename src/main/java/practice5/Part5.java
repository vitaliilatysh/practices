package practice5;

import java.io.File;
import java.io.IOException;

public class Part5 {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
        Thread[] createdThreads = new Part5().createThreads(10, threadGroup);

        removeFileIfExists("part5.txt");

        runAllCreatedThreads(createdThreads);

        printFileContent();
    }

    private static void printFileContent() {
        Util.readAllLines("part5.txt").forEach(System.out::println);
    }

    private static void runAllCreatedThreads(Thread[] createdThreads) throws InterruptedException {
        for (Thread thread : createdThreads) {
            thread.start();
        }

        for (Thread thread : createdThreads) {
            thread.join();
        }

    }


    private static void removeFileIfExists(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private Thread[] createThreads(int numberOfThreads, ThreadGroup threadGroup) {
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(threadGroup, new Writer());
        }
        return threads;
    }

    class Writer implements Runnable {

        @Override
        public void run() {
            try {
                Util.writeToFile("part5.txt");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
