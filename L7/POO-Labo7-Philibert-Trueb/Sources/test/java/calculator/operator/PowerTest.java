package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PowerTest {
    @Test
    public void shouldSquare() {
        State state = new State();
        state.setValue("3.0");

        new Power(state, 2).execute();

        assertEquals("9.0", state.getValue());
    }
}
