package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClearTest {
    @Test
    public void shouldClearError() {
        State state = new State();
        state.setIsError(true);

        new Clear(state).execute();

        assertFalse(state.getIsError());
    }

    @Test
    public void shouldClearOperands() {
        State state = new State();
        state.getOperands().push(3.0);
        state.getOperands().push(2.5);

        new Clear(state).execute();

        assertEquals(0, state.getOperands().size());
    }
}
