package main.com.softuni.bashsoft.data_structures;

import main.com.softuni.bashsoft.contracts.SimpleOrderedBag;
import main.com.softuni.bashsoft.static_data.ExceptionMessages;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class SimpleSortedList<T extends Comparable<T>> implements SimpleOrderedBag<T> {

    private static final int DEFAULT_SIZE = 16;
    private T[] innerCollection;
    private int size;
    private Comparator<T> comparator;

    public SimpleSortedList(Class<T> type, Comparator<T> comparator, int capacity) {
        this.initializeInnerCollection(type, capacity);
        this.comparator = comparator;
    }

    public SimpleSortedList(Class<T> type, int capacity) {
        this(type, Comparable::compareTo, capacity);
    }

    public SimpleSortedList(Class<T> type, Comparator<T> comparator) {
        this(type, comparator, DEFAULT_SIZE);
    }

    public SimpleSortedList(Class<T> type) {
        this(type, Comparable::compareTo, DEFAULT_SIZE);
    }

    @Override
    public boolean remove(T element) {
        boolean hasBeenRemoved = false;
        int indexOfRemovedElement = 0;
        if(element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.size(); i++) {
            if(this.innerCollection[i].compareTo(element) == 0) {
                indexOfRemovedElement = i;
                this.innerCollection[i] = null;
                hasBeenRemoved = true;
                break;
            }
        }
        if(hasBeenRemoved) {
            for (int i = indexOfRemovedElement; i < this.size() - 1; i++) {
                this.innerCollection[i] = this.innerCollection[i + 1];
            }
            this.innerCollection[this.size() - 1] = null;
            this.size--;
        }
        return hasBeenRemoved;
    }

    @Override
    public int capacity() {
        return this.innerCollection.length;
    }

    @Override
    public void add(T item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        if(this.size() >= this.innerCollection.length) {
            this.resize();
        }
        this.innerCollection[this.size()] = item;
        this.size++;
        Arrays.sort(this.innerCollection, 0, this.size(), this.comparator);
    }

    @Override
    public void addAll(Collection<T> items) {
        if(items == null) {
            throw new IllegalArgumentException();
        }
        if(this.size() + items.size() >= this.innerCollection.length) {
            this.multiResize(items);
        }
        for (T item : items) {
            this.innerCollection[this.size()] = item;
            this.size++;
        }
        Arrays.sort(this.innerCollection, 0, this.size, this.comparator);
    }

    private void multiResize(Collection<T> items) {
        int newSize = this.innerCollection.length * 2;
        while(this.size() + items.size() >= newSize) {
            newSize *= 2;
        }
        T[] newCollection = Arrays.copyOf(
                this.innerCollection, newSize);
        this.innerCollection = newCollection;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String joinWith(String joiner) {
        if(joiner == null) {
            throw new NullPointerException();
        }
        StringBuilder builder = new StringBuilder();
        for (T t : this) {
            builder.append(t).append(joiner);
        }
        builder.setLength(builder.length() - joiner.length());
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedListIterator();
    }

    @SuppressWarnings("unchecked")
    private void initializeInnerCollection(Class<T> type, int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.CAPACITY_CANNOT_BE_NEGATIVE);
        }
        this.innerCollection = (T[]) Array.newInstance(type, capacity);
    }

    private void resize() {
        T[] newCollection = Arrays.copyOf(
                this.innerCollection,
                this.innerCollection.length * 2);
        this.innerCollection = newCollection;
    }

    private class SortedListIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index < size();
        }

        @Override
        public T next() {
            return innerCollection[this.index++];
        }
    }
}
