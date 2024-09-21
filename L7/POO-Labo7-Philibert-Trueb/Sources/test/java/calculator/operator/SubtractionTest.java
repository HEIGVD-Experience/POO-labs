package calculator.operator;


import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubtractionTest {
    @Test
    public void shouldSubtractValues() {
        State state = new State();
        state.setValue("7.5");
        state.getOperands().push(4.0);

        new Subtraction(state).execute();

        assertEquals("3.5", state.getValue());
        assertTrue(state.getIsResult());
    }

    @Test
    public void shouldNotSubtractWhenOperandEmpty() {
        State state = new State();
        state.setValue("5");

        new Subtraction(state).execute();

        assertEquals("5", state.getValue());
    }

    @Test
    public void shouldNotSubtractWhenIsError() {
        State state = new State();
        state.setIsError(true);
        state.setValue("5");
        state.getOperands().push(3.0);

        new Subtraction(state).execute();

        assertEquals("5", state.getValue());
    }
}
