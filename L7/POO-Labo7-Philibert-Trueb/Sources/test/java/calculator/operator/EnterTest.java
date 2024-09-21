package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnterTest {
    @Test
    public void shouldPushValueToOperands() {
        State state = new State();
        state.setValue("3.75");

        new Enter(state).execute();

        assertEquals(3.75, state.getOperands().pop());
        assertEquals("0", state.getValue());
    }
}
