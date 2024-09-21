package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryStoreTest {
    @Test
    public void shouldStoreValue() {
        State state = new State();
        state.setValue("4.25");

        new MemoryStore(state).execute();

        assertEquals(4.25, state.getMemory());
    }
}
