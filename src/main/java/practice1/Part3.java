package practice1;

public class Part3 {
    public static void main(String[] args) {
        for(String arg: args) {
            String [] newArray = arg.split(" ");
            for(String newElement: newArray) {
                if(!newElement.isEmpty()) {
                    System.out.print(newElement + " ");
                }
            }
        }
    }
}
