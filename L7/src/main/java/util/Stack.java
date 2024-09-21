package util;

import util.SingleLinkedList.SingleLinkedList;

import java.util.Iterator;
import java.util.StringJoiner;

public class Stack<T> implements Iterable<T> {

    private final SingleLinkedList<T> list;

    public Stack() {
        list = new SingleLinkedList<>();
    }

    public void push(T value) {
        list.addFirst(value);
    }

    public T pop() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void toArray(T[] arr) {
        if (arr.length < list.size()) {
            throw new RuntimeException("Array is smaller than Stack");
        }

        Iterator<T> it = iterator();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = it.next();
        }
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringJoiner s = new StringJoiner(" ");
        Iterator<T> it = iterator();

        while (it.hasNext()) {
            s.add(it.next().toString());
        }

        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
