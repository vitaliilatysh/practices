package practice1;

public class Part4 {
    public static void main(String[] args) {
        int value1 = Integer.valueOf(args[0]);
        int value2 = Integer.valueOf(args[1]);

        int i = 1;
        int max = 0;
        if (value1 > value2) {
            for (; i <= value2; i++) {
                if (value1 % i == 0 && value2 % i == 0) {
                    if (i > max) {
                        max = i;
                    }
                }
            }
        } else {
            for (; i <= value1; i++) {
                if (value1 % i == 0 && value2 % i == 0) {
                    if (i > max) {
                        max = i;
                    }
                }
            }
        }
        System.out.println(max);

    }
}
