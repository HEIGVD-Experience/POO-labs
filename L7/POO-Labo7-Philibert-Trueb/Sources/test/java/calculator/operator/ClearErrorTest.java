package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClearErrorTest {
    @Test
    public void shouldClearError() {
        State state = new State();
        state.setIsError(true);

        new ClearError(state).execute();

        assertFalse(state.getIsError());
    }

    @Test
    public void shouldClearValue() {
        State state = new State();
        state.setIsError(true);
        state.setValue("3.5");

        new ClearError(state).execute();

        assertEquals("0", state.getValue());
    }
}
