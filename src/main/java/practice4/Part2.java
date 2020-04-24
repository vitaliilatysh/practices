package practice4;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Part2 {

    private static final int COUNT = 10;
    private static final String ENCODING = "windows-1251";
    private static final String FILE1 = "part2.txt";
    private static final String FILE2 = "part2_sorted.txt";

    public static void main(String[] args) throws IOException {
        int[] arrayToWrite = createArray(COUNT);
        Util.write(arrayToWrite, FILE1, ENCODING);

        String fileData = Util.read(FILE1, ENCODING);
        int [] arrayToSort = toArray(fileData);

        sort(arrayToSort);
        Util.write(arrayToSort, FILE2, ENCODING);

        System.out.println("input ==> " + Util.read(FILE1, ENCODING));
        System.out.println("output ==> " + Util.read(FILE2, ENCODING));

    }

    private static void sort(int[] arrayToSort) {
        Arrays.sort(arrayToSort);
    }

    private static int[] createArray(int capacity) {

        int[] array = new int[capacity];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(50);
        }
        return array;
    }

    private static int[] toArray(String fileData){
        String[] elementsOfFileData = fileData.split(" ");
        int[] arrayToReturn = new int[elementsOfFileData.length];

        for(int i = 0; i < arrayToReturn.length; i++){
            arrayToReturn[i] = Integer.valueOf(elementsOfFileData[i]);
        }

        return arrayToReturn;
    }

}
