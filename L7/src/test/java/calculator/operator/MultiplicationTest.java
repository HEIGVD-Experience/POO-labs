package calculator.operator;


import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplicationTest {
    @Test
    public void shouldMultiplyValues() {
        State state = new State();
        state.setValue("2");
        state.getOperands().push(3.0);

        new Multiplication(state).execute();

        assertEquals("6.0", state.getValue());
        assertTrue(state.getIsResult());
    }

    @Test
    public void shouldNotMultiplyWhenOperandEmpty() {
        State state = new State();
        state.setValue("5");

        new Multiplication(state).execute();

        assertEquals("5", state.getValue());
    }

    @Test
    public void shouldNotMultiplyWhenIsError() {
        State state = new State();
        state.setIsError(true);
        state.setValue("5");
        state.getOperands().push(3.0);

        new Multiplication(state).execute();

        assertEquals("5", state.getValue());
    }
}
