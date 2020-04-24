package practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    public static void main(String[] args) {
        readResourcesAndPrint();
    }

    private static void readResourcesAndPrint() {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle bundle;
        String locale;

        while (scanner.hasNext()) {
            String[] lineParams = scanner.nextLine().split(" ");
            if(!lineParams[0].equalsIgnoreCase("stop")) {
                locale = lineParams[1];
                switch (locale) {
                    case "ru":
                        bundle = ResourceBundle.getBundle("resources", new Locale(locale));
                        System.out.println(bundle.getString(lineParams[0]));
                        continue;
                    case "en":
                        bundle = ResourceBundle.getBundle("resources", new Locale(locale));
                        System.out.println(bundle.getString(lineParams[0]));

                }
            } else {
                break;
            }
        }
    }
}


