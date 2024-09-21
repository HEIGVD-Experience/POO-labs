package calculator.operator;


import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionTest {
    @Test
    public void shouldAddValues() {
        State state = new State();
        state.setValue("3.5");
        state.getOperands().push(4.0);

        new Addition(state).execute();

        assertEquals("7.5", state.getValue());
        assertTrue(state.getIsResult());
    }

    @Test
    public void shouldNotAddWhenOperandEmpty() {
        State state = new State();
        state.setValue("5");

        new Addition(state).execute();

        assertEquals("5", state.getValue());
    }

    @Test
    public void shouldNotAddWhenIsError() {
        State state = new State();
        state.setIsError(true);
        state.setValue("5");
        state.getOperands().push(3.0);

        new Addition(state).execute();

        assertEquals("5", state.getValue());
    }
}
