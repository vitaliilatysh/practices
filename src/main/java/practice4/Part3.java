package practice4;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String fileData = Util.read("part3.txt", "windows-1251");

        while (scanner.hasNext()) {
            String arg = scanner.nextLine();
            String typeToPrint = arg.toLowerCase();

            switch (typeToPrint) {
                case "char":
                    findAndPrintValues(fileData, "\\b\\w\\b");
                    continue;
                case "string":
                    findAndPrintValues(fileData, "\\b(?!\\d)\\w{2,}\\b");
                    continue;
                case "double":
                    findAndPrintValues(fileData, "(\\d+)?\\.\\d+|\\d+\\.");
                    continue;
                case "int":
                    findAndPrintValues(fileData, "(?<=\\s|^)\\d+(?=\\s|$)");
                    continue;
                case "stop":
                    break;
            }
        }
    }

    private static void findAndPrintValues(String fileData, String regex) {

        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(fileData);
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            stringBuilder.append(matcher.group()).append(" ");
        }

        System.out.println(stringBuilder.toString());
    }
}
