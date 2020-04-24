package practice5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part4 {

    public static void main(String[] args) throws InterruptedException {
        List<String> amountOfLines = Util.readAllLines("part4.txt");

        new Part4().multiThread(amountOfLines);
        new Part4().singleThread(amountOfLines);
    }

    private void singleThread(List<String> myData) throws InterruptedException {

        String[] newArray = new String[myData.size()];
        int finalMax = Integer.MIN_VALUE;

        long startTime = System.currentTimeMillis();
        for (String s : myData.toArray(newArray)) {
            String[] splited = s.split("\\s+");
            int max = Arrays.stream(splited)
                    .map(Integer::parseInt)
                    .collect(Collectors.summarizingInt(Integer::intValue)).getMax();
            if (finalMax < max) {
                Thread.sleep(30);
                finalMax = max;
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println(finalMax);
        System.out.println(endTime - startTime);
    }

    private void multiThread(List<String> amountOfLines) throws InterruptedException {

        Thread[] threads = new Thread[amountOfLines.size()];
        Line[] lines = new Line[amountOfLines.size()];

        for (int i = 0; i < amountOfLines.size(); i++) {
            lines[i] = new Line(amountOfLines.get(i).split("\\s+"));
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amountOfLines.size(); i++) {
            threads[i] = new Thread(lines[i]);
            threads[i].start();
        }
        long endTime = System.currentTimeMillis();

        int finalMax = lines[0].getMax(); //Sets max as first worker's max

            for (Line line : lines) {
                if (finalMax < line.getMax())
                    finalMax = line.getMax();

        }


        System.out.println(finalMax);

        System.out.println(endTime - startTime);
    }

    public static class Line implements Runnable {

        int max = Integer.MIN_VALUE;
        private String[] randomNumbers;

        Line(String[] randomNumbers) {
            this.randomNumbers = randomNumbers;
        }

        @Override
        public synchronized void run() {
            int[] array = new int[randomNumbers.length];

            for (int i = 0; i < randomNumbers.length; i++) {
                array[i] = Integer.parseInt(randomNumbers[i]);
            }

            for (int element : array) {
                if (element > max)
                    max = element;
            }
        }

        int getMax() {
            return max;
        }
    }
}
