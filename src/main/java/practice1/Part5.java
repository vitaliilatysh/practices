package practice1;

public class Part5 {
    public static void main(String[] args) {

        char[] array = args[0].toCharArray();
        int sum = 0;
        for(char charValue: array) {
            sum += Character.getNumericValue(charValue);
        }
        System.out.println(sum);
    }
}
