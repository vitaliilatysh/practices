package practice3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String input = Util.readFile("part1.txt");

        System.out.println(Part1.convert1(input));
        System.out.println(Part1.convert2(input));
        System.out.println(Part1.convert3(input));
        System.out.println(Part1.convert4(input));
    }

    private static String convert1(String input) {

        Pattern pattern = Pattern.compile("(.*);(.*);(.*)");
        Matcher matcher = pattern.matcher(input);
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            if (matcher.group(1).equals("Login")) {
            } else {
                stringBuilder.append(matcher.group(1)).append(" ==> ").append(matcher.group(3)).append("\n");
            }
        }

        return stringBuilder.toString();
    }

    private static String convert2(String input) {
        Pattern pattern = Pattern.compile("(.*);(.*);(.*)");
        Matcher matcher = pattern.matcher(input);
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            if (matcher.group(1).equals("Login")) {
            } else {
                String[] fullname = matcher.group(2).split(" ");

                stringBuilder.append(fullname[1] + " " + fullname[0])
                        .append(" (email: ")
                        .append(matcher.group(3) + ")")
                        .append("\n");
            }
        }

        return stringBuilder.toString();
    }

    private static String convert3(String input) {
        Pattern pattern = Pattern.compile("(.*);(.*);(.*)");
        Matcher matcher = pattern.matcher(input);

        StringBuilder finalOutput = new StringBuilder();
        List<StringBuilder> tempResult = new ArrayList<>();
        Map<String, List<String>> mapOfUniqueDomainWith = new LinkedHashMap<>();
        while (matcher.find()) {
            if (matcher.group(1).equals("Login")) {
            } else {
                String [] email = matcher.group(3).split("@");
                String domainName = email[1];
                String [] userNamesFromFile = matcher.group(1).split("\\s+");
                String userName = userNamesFromFile[0];

                if(!mapOfUniqueDomainWith.containsKey(domainName)) {
                    List<String> userNames = new ArrayList<>();
                    userNames.add(userName);
                    mapOfUniqueDomainWith.put(domainName, userNames);
                } else{
                    mapOfUniqueDomainWith.get(domainName).add(userName);
                }

                StringBuilder result = new StringBuilder();
                for (String keyDomain: mapOfUniqueDomainWith.keySet()){
                    List<String> userNamesList = mapOfUniqueDomainWith.get(keyDomain);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(keyDomain).append(" ==> ");
                    stringBuilder.append(String.join(", ", userNamesList));
                    stringBuilder.append('\n');

                    result.append(stringBuilder);
                    tempResult.add(stringBuilder);
                }
            }
        }
        List<StringBuilder> newList = tempResult.subList(tempResult.size() - mapOfUniqueDomainWith.keySet().size(), tempResult.size());

        for(StringBuilder line: newList) {
            finalOutput.append(line);
        }

        return finalOutput.toString();
    }

    private static String convert4(String input) {
        Pattern pattern = Pattern.compile("(.*);(.*);(.*)");
        Matcher matcher = pattern.matcher(input);
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            if (matcher.group(1).equals("Login")) {
                stringBuilder.append(matcher.group(1)).append(";")
                        .append(matcher.group(2)).append(";").append(matcher.group(3)).append(";Password").append("\n");
            } else {
                stringBuilder.append(matcher.group(1)).append(";")
                        .append(matcher.group(2)).append(";").append(matcher.group(3)).append(";")
                        .append(String.format("%04d", new Random().nextInt(10000))).append("\n");
            }
        }

        return stringBuilder.toString();
    }

}
