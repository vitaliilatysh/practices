package practice4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Part4 {
    public static void main(String[] args) throws IOException {
        String [] myData = Util.read("part4.txt", "windows-1251").split("\\.( |$|\\n)");
        List<String> parsedSentences = new ArrayList<>();

        Collections.addAll(parsedSentences, myData);

        DataSet dataSet = new DataSet(parsedSentences);
        Iterator<String> iterator = dataSet.iterator();
        while (iterator.hasNext()) {
            String nextValue = iterator.next().replaceAll("\n", "");
            System.out.println(nextValue + ".");
        }
    }
}

class DataSet implements Iterable<String> {

    private List<String> myData;

    DataSet(List<String> myData){
        this.myData = myData;
    }
    @Override
    public Iterator<String> iterator() {
        return new DataSetIterator();
    }

    private class DataSetIterator implements Iterator {
        private int position = 0;

        public boolean hasNext() {
            if (position < myData.size())
                return true;
            else
                return false;
        }

        public String next() {
            if (this.hasNext())
                return myData.get(position++);
            else
                return null;
        }

        @Override
        public void remove() {

        }
    }

}


