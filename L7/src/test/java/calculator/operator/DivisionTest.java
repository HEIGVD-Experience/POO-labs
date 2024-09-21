package calculator.operator;


import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest {
    @Test
    public void shouldDivideValues() {
        State state = new State();
        state.setValue("2.0");
        state.getOperands().push(6.0);

        new Division(state).execute();

        assertEquals("3.0", state.getValue());
        assertTrue(state.getIsResult());
    }

    @Test
    public void shouldNotDivideWhenOperandEmpty() {
        State state = new State();
        state.setValue("5");

        new Division(state).execute();

        assertEquals("5", state.getValue());
    }

    @Test
    public void shouldNotDivideWhenIsError() {
        State state = new State();
        state.setIsError(true);
        state.setValue("5");
        state.getOperands().push(3.0);

        new Division(state).execute();

        assertEquals("5", state.getValue());
    }

    @Test
    public void shouldErrorWhenOperandsEmpty() {
        State state = new State();
        state.setValue("6");

        new Division(state).execute();

        assertTrue(state.getIsError());
    }

    @Test
    public void shouldErrorWhenDividingByZero() {
        State state = new State();
        state.getOperands().push(3.0);
        state.setValue("0.0");

        new Division(state).execute();

        assertEquals("Infinity", state.getValue());
    }
}
