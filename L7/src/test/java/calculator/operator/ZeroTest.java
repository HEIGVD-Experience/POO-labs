package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZeroTest {
    @Test
    public void shouldNotAddZeroIfAlreadyZero() {
        State state = new State();
        state.setValue("0");

        new Zero(state).execute();

        assertEquals("0", state.getValue());
    }
}
