package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryRecallTest {
    @Test
    public void shouldRecallStoredValue() {
        State state = new State();
        state.setMemory(4.75);

        new MemoryRecall(state).execute();

        assertEquals("4.75", state.getValue());
    }

    @Test
    public void shouldNotRecallWhenIsError() {
        State state = new State();
        state.setMemory(4.75);
        state.setIsError(true);

        new MemoryRecall(state).execute();

        assertTrue(state.getIsError());
    }
}
