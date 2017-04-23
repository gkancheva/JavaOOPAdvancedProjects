package main.com.softuni.bashsoft.contracts;

import java.util.Collection;

public interface SimpleOrderedBag<T extends Comparable<T>> extends Iterable<T> {

    boolean remove(T element);

    int capacity();

    void add(T item);

    void addAll(Collection<T> items);

    int size();

    String joinWith(String joiner);
}
