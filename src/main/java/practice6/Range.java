package practice6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Range implements Iterable<Integer> {
    private List<Integer> array;

    public Range(int startRange, int endRange) {
        this.array = new ArrayList<>();
        for (int i = startRange; i <= endRange; i++) {
            array.add(i);
        }
    }

    public Range(int startRange, int endRange, boolean reverse) {
        if (reverse) {
            this.array = new ArrayList<>();
            for (int i = endRange; i >= startRange; i--) {
                array.add(i);
            }
        } else {
            this.array = new ArrayList<>();
            for (int i = startRange; i <= endRange; i++) {
                array.add(i);
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator {
        private int position = 0;

        public boolean hasNext() {
            if (position < array.size())
                return true;
            else
                return false;
        }

        public Integer next() {
            if (this.hasNext())
                return array.get(position++);
            else
                return null;
        }

        @Override
        public void remove() {

        }
    }
}
