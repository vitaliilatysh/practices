package practice2;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl implements MyList, ListIterable {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size = 0;

    public MyListImpl() {
        this.array = new Object[]{DEFAULT_CAPACITY};
    }

    @Override
    public void add(Object e) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size++] = e;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(array[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
//        Iterator<Object> it = iterator();
//        if (o==null) {
//            while (it.hasNext())
//                if (it.next()==null)
//                    return true;
//        } else {
//            while (it.hasNext())
//                if (o.equals(it.next()))
//                    return true;
//        }
//        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
//        for (Object e : c)
//            if (!contains(e))
//                return false;
//        return true;
        for (int position = 0; position < c.size(); position++) {
            if (!contains(c.toArray()[position])) {
                return false;
            }
        }
        return true;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index + 1, array, index,
                    numMoved);
        array[--size] = null;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null)
                    return i;

        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public Iterator<Object> iterator() {
        return (Iterator<Object>) new IteratorImpl();
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public Object previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = MyListImpl.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return elementData[lastRet = i];
        }

        @Override
        public void set(Object e) {

        }
    }


    private class IteratorImpl implements Iterator<Object> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        // returns true if the iteration has more elements
        public boolean hasNext() {
            return cursor != size;
        }

        // returns the next element in the iteration
        public Object next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyListImpl.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return elementData[lastRet = i];
        }

        // removes from the underlying collection
        // the last element returned by this iterator
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyListImpl.this.remove(array[lastRet]);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
