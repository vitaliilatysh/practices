package practice1;

public class Demo {
    public static void main(String[] args) {
        System.out.println("====Part1====");
        Part1.main(new String[0]);

        System.out.println("====Part2====");
        Part2.main(new String[]{"2", "3"});

        System.out.println("====Part3====");
        Part3.main(new String[]{"a b    c d    34"});
        System.out.println();

        System.out.println("====Part4====");
        Part4.main(new String[]{"25","15"});

        System.out.println("====Part5====");
        Part5.main(new String[]{"1234"});

        System.out.println("====Part6====");
        Part6.main(new String[]{"7"});

        System.out.println("====Part7====");
        Part7.main(new String[]{"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"});
    }
}
