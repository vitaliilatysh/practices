package practice3;

import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        String input = Util.readFile("part2.txt");
        System.out.println(Part2.convert(input));
    }

    private static String convert(String input) {
        String stringMax, stringMin;
        String[] words = input.split("([ ]|[.]|[,]|[:]|[?]|[']|[\\s+])+");
        List<String> listMax = new ArrayList<>();
        List<String> listMin = new ArrayList<>();

        String maxWord = "";
        for (String word : words) {
            if (word.length() >= maxWord.length()) {
                maxWord = word;
            }
        }

        for(String word: words){
            if(word.length() >= maxWord.length()){
                if(!listMax.contains(word))
                    listMax.add(word);
            }
        }

        String minWord = "a";
        for (String word : words) {
            if (word.length() <= minWord.length()) {
                minWord = word;
            }
        }
        for (String word : words) {
            if(word.length() <= minWord.length()){
                if(!listMin.contains(word))
                    listMin.add(word);
            }
        }
        stringMin = String.join(", ", listMin);
        stringMax = String.join(", ", listMax);

        return "Min: ".concat(stringMin.concat("\n")).concat("Max: ").concat(stringMax);
    }

}
