package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlipSignTest {
    @Test
    public void shouldFlipSignPositiveToNegative() {
        State state = new State();
        state.setValue("3.15");

        new FlipSign(state).execute();

        assertEquals("-3.15", state.getValue());
    }

    @Test
    public void shouldFlipSignNegativeToPositive() {
        State state = new State();
        state.setValue("-3.15");

        new FlipSign(state).execute();

        assertEquals("3.15", state.getValue());
    }

    @Test
    public void shouldFlipSignWhenValueIsZero() {
        State state = new State();
        state.setValue("0");

        new FlipSign(state).execute();

        assertEquals("-0", state.getValue());
    }
}
