package practice3;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) {
        String input = Util.readFile("part3.txt");

        System.out.println(Part3.convert(input));
    }

    private static String convert(String input) {
        Pattern pattern = Pattern.compile("\\b(\\w)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        String[] words = input.split("([ ]|[.]|[,]|[:]|[?]|[']|[\\s+])+", Pattern.UNICODE_CHARACTER_CLASS);
        StringBuffer stringBuffer = new StringBuffer(input.length());

       for(String word: words) {
           if (matcher.find()) {
               if (word.length() >= 3) {
                   matcher.appendReplacement(stringBuffer, matcher.group().toUpperCase());
               } else {
                   matcher.appendReplacement(stringBuffer, matcher.group());
               }
           }
       }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
