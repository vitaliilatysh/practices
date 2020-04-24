package practice6;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Part6 {

    public static void main(String[] args) {

        String[] myData = null;
        if (args[0].equalsIgnoreCase("-i") | args[0].equalsIgnoreCase("--input")) {
            myData = Util.read(args[1], "windows-1251").split("\\s+");
        }
        if (args[2].equalsIgnoreCase("-t") | args[2].equalsIgnoreCase("--task"))
            switch (args[3]) {
                case "length":
                    get3MostLongWords(myData);
                    break;
                case "frequency":
                    get3MostFrequentWords(myData);
                    break;
                case "duplicates":
                    get3DuplicatedWords(myData);
                    break;
            }
    }

    private static void get3DuplicatedWords(String[] myData) {
        Stream.of(myData)
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, summingInt(e -> 1)))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .limit(3)
                .map(entry -> entry.getKey().toUpperCase())
                .forEach(System.out::println);
    }

    private static void get3MostFrequentWords(String[] myData) {
        Stream.of(myData)
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, summingInt(e -> 1)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new))
                .forEach((key, value) -> System.out.println(key + " ==> " + value));
    }

    private static void get3MostLongWords(String[] myData) {
        Map<String, Integer> result = Stream.of(myData)
                .collect(LinkedHashMap::new, (map, itemVar) -> map.put(itemVar, itemVar.length()), Map::putAll);

        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new))
                .forEach((key, value) -> System.out.println(key + " ==> " + value));
    }
}