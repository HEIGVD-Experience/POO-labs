package util.SingleLinkedList;

import java.util.Iterator;

public class SingleLinkedList<T> implements Iterable<T> {
    private final Node<T> beforeHead;

    public SingleLinkedList() {
        beforeHead = new Node<>(null);
    }

    public void addFirst(T value) {
        Node<T> currentHead = beforeHead.getNext();
        beforeHead.setNext(new Node<T>(value, currentHead));
    }

    public boolean isEmpty() {
        return beforeHead.getNext() == null;
    }

    public void clear() {
        beforeHead.setNext(null);
    }

    public T head() {
        Node<T> head = beforeHead.getNext();

        return head == null ? null : head.getValue();
    }

    public T removeFirst() {
        Node<T> head = beforeHead.getNext();
        T value = head.getValue();
        beforeHead.setNext(head.getNext());

        return value;
    }

    public int size() {
        int size = 0;

        Node<T> curr = beforeHead;
        while ((curr = curr.getNext()) != null) {
            size++;
        }

        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<>(beforeHead);
    }

    private static class Node<T> {
        private final T value;

        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next) {
            this(value);
            this.next = next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }
    }

    public static class SingleLinkedListIterator<T> implements Iterator<T> {

        private Node<T> curr;

        public SingleLinkedListIterator(Node<T> beforeHead) {
            this.curr = beforeHead;
        }

        @Override
        public boolean hasNext() {
            return curr.getNext() != null;
        }

        @Override
        public T next() {
            curr = curr.getNext();

            return curr.getValue();
        }
    }
}
