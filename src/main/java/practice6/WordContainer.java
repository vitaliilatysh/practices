package practice6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// you can extend this class from one of the core container
// or aggregate it inside of class 
class WordContainer<T> extends ArrayList<T> {

    public void main(Scanner scanner) {

        WordContainer<Word> words = new WordContainer<>();

        while (scanner.hasNext()) {
            String inputWord = scanner.next();
            if(!inputWord.equals("stop")) {
                Word word = new Word(inputWord);
                if(!words.contains(word)) {
                    words.add(word);
                } else{
                    int indexWord = words.indexOf(word);
                    words.get(indexWord).incrementFrequency();
                }
            }
        }

        Collections.sort(words);

        for(Word word: words){
            System.out.println(word.getWord() + " : " + word.getFrequency());
        }

    }
}
