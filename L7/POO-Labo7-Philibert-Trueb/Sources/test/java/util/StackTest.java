package util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    public void shouldHaveCorrectLength() {
        Stack<Integer> stack = new Stack<>();

        stack.push(34);
        stack.push(5);
        stack.push(1);

        assertEquals(3, stack.size());
    }

    @Test
    public void shouldHaveCorrectPopValue() {
        Stack<Integer> stack = new Stack<>();

        stack.push(34);
        stack.push(5);

        assertEquals(5, stack.pop());
    }

    @Test
    public void shouldBeEmpty() {
        Stack<Integer> stack = new Stack<>();

        stack.push(3);
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldEmptyStackIteratorHasNextBeEmpty() {
        Stack<Integer> stack = new Stack<>();

        assertFalse(stack.iterator().hasNext());
    }

    @Test
    public void shouldIteratorIterate() {
        Stack<Integer> stack = new Stack<>();

        stack.push(45);
        stack.push(8);
        stack.push(6);

        assertIterableEquals(List.of(6, 8, 45), stack);
    }

    @Test
    public void shouldFillArrayWithStackValues() {
        Stack<Integer> stack = new Stack<>();

        stack.push(5);
        stack.push(6);
        stack.push(7);

        Integer[] arr = new Integer[3];
        stack.toArray(arr);

        assertArrayEquals(new Integer[]{7, 6, 5}, arr);
    }

    @Test
    public void shouldClearStack() {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();

        assertTrue(stack.isEmpty());
    }
}
