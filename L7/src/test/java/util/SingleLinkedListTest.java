package util;

import org.junit.jupiter.api.Test;
import util.SingleLinkedList.SingleLinkedList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SingleLinkedListTest {

    @Test
    void shouldAddAtBeginning() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        list.addFirst(6);
        list.addFirst(3);

        assertEquals(3, list.head());
    }

    @Test
    void listShouldBeEmpty() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        assertTrue(list.isEmpty());
    }

    @Test
    void listShouldNotBeEmpty() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        list.addFirst(7);

        assertFalse(list.isEmpty());
    }

    @Test
    void listShouldHaveSizeZero() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        assertEquals(0, list.size());
    }

    @Test
    void listShouldHaveSize() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        list.addFirst(2);
        list.addFirst(2);
        list.addFirst(2);

        assertEquals(3, list.size());
    }

    @Test
    void listShouldRemoveFirst() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        list.addFirst(3);
        list.addFirst(7);
        list.removeFirst();

        assertEquals(1, list.size());
    }

    @Test
    void iteratorShouldIterate() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        assertIterableEquals(List.of(1,2,3), list);
    }
}
