package practice4;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Part1().convert(Util.read("part1.txt", "windows-1251")));
    }

    String convert(String input) {

        Pattern pattern = Pattern.compile("\\b(\\w+)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);

        String [] words = input.split("([ ]|[.]|[,]|[:]|[?]|[']|[-]|[\\s+])+", Pattern.UNICODE_CHARACTER_CLASS);
        StringBuffer stringBuffer = new StringBuffer();

        for(String word: words) {
            if (matcher.find()) {
                if (word.length() > 3) {
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
