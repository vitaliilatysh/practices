package practice3;

import java.util.TreeMap;

public class Part5 {
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static void main(String[] args) {

        for (String arg : new String[]{"1", "2", "3", "4", "5", "94", "95", "96", "97", "98", "99", "100"}) {
            String decimal2RomanValue = decimal2Roman(Integer.valueOf(arg));
            int roman2Decimal = roman2Decimal(decimal2RomanValue);
            System.out.println(arg
                    .concat(" ====> ")
                    .concat(decimal2RomanValue)
                    .concat(" ====> ")
                    .concat(String.valueOf(roman2Decimal)));
        }
    }

    private static String decimal2Roman(int x) {
        int l = map.floorKey(x);
        if (x == l) {
            return map.get(x);
        }
        return map.get(l) + decimal2Roman(x - l);
    }

    private static int roman2Decimal(String s) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = s.toUpperCase();

        for (int x = romanNumeral.length() - 1; x >= 0; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return decimal;
    }

    private static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }

}
