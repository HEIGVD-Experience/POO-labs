package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareRootTest {
    @Test
    public void shouldSqrtValues() {
        State state = new State();
        state.setValue("4");

        new SquareRoot(state).execute();

        assertEquals("2.0", state.getValue());
    }

    @Test
    public void shouldNotSqrtWhenIsError() {
        State state = new State();
        state.setIsError(true);
        state.setValue("4.0");

        new SquareRoot(state).execute();

        assertEquals("4.0", state.getValue());
    }
}
