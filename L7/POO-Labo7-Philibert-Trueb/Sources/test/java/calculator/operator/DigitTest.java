package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DigitTest {
    @Test
    public void shouldAppendDigitToValue() {
        State state = new State();
        state.setValue("3");

        new Digit(state, 5).execute();

        assertEquals("35", state.getValue());
    }

    @Test
    public void shouldPushOperandWhenIsResult() {
        State state = new State();
        state.setValue("12.5");
        state.setIsResult(true);

        new Digit(state, 4).execute();

        assertEquals("4", state.getValue());
        assertEquals(12.5, state.getOperands().pop());
    }

    @Test
    public void shouldReplaceZeroWhenZeroIsNegative() {
        State state = new State();
        state.setValue("-0");

        new Digit(state, 3).execute();

        assertEquals("-3", state.getValue());
    }
}
