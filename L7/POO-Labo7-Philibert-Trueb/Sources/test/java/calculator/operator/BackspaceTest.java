package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackspaceTest {
    @Test
    public void shouldRemoveValue() {
        State state = new State();
        state.setValue("23.5");

        new Backspace(state).execute();

        assertEquals("23.", state.getValue());
    }

    @Test
    public void shouldReplaceWithZeroIfValueBecomesEmpty() {
        State state = new State();
        state.setValue("2");

        new Backspace(state).execute();

        assertEquals("0", state.getValue());
    }
}
