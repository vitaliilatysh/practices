package practice1;

public class Part7 {
    public static void main(String[] args) {

        if(args[0].equals("EMPTY")) {
            for (String arg : new String[]{"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"}) {
                String char2digitValue = arg.toUpperCase();
                int digit2charValue = str2int(char2digitValue);

                System.out.println(
                        char2digitValue + " ==> " +
                                str2int(char2digitValue) + " ==> " +
                                int2str(digit2charValue)
                );
            }
        }
    }

    public static int str2int(String number) {
        char[] charsArray = number.toCharArray();

        int position = 0;
        for (int elementPosition = 0; elementPosition < charsArray.length; elementPosition++) {
            position *= 26;
            position += charsArray[elementPosition] - 65 + 1;
        }
        return position;
    }

    public static String int2str(int number) {
        StringBuilder sb = new StringBuilder();

        int num = number - 1;
        while (num >=  0) {
            int numChar = (num % 26) + 65;
            sb.append((char)numChar);
            num = (num / 26) - 1;
        }
        return sb.reverse().toString();
    }

    public static String rightColumn(String number) {
        int returnedPosition = str2int(number);
        return int2str(returnedPosition+1);
    }
}
