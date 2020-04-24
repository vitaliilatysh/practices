package practice6;

import java.util.Comparator;
import java.util.Objects;

public class Word implements Comparable<Word> {

     String getWord() {
        return word;
    }

    private String word;

     int getFrequency() {
        return frequency;
    }

    private int frequency;

    Word(String word) {
        this.word = word;
        frequency = 1;
    }

    @Override
    public int compareTo(Word word) {
        return Comparator
                .comparing(Word::getFrequency)
                .thenComparing(Word::getWord, Comparator.reverseOrder())
                .compare(word, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    public void incrementFrequency() {
        this.frequency++;
    }
}
