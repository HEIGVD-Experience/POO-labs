package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvertTest {
    @Test
    public void shouldInvertValues() {
        State state = new State();
        state.setValue("10.0");

        new Invert(state).execute();

        assertEquals("0.1", state.getValue());
    }

    @Test
    public void shouldErrorWhenValueIsZero() {
        State state = new State();
        state.setValue("0.0");

        new Invert(state).execute();

        assertTrue(state.getIsError());
    }
}
