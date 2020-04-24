package practice1;

public class Part6 {
    public static void main(String[] args) {

        int value = Integer.valueOf(args[0]);
        int counter = 0;

        for (int i = 2; counter < value; i++) {
            int divideCounter = 0;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    ++divideCounter;
                }
            }
            if (divideCounter == 0) {
                System.out.print(i + " ");
                ++counter;
            }
        }
        System.out.println();
    }
}

