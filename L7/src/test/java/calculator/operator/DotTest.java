package calculator.operator;

import calculator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DotTest {
    @Test
    public void shouldPutDot() {
        State state = new State();
        state.setValue("32");

        new Dot(state).execute();

        assertEquals("32.", state.getValue());
    }

    @Test
    public void shouldNotPutDotIfValueAlreadyHasOne() {
        State state = new State();
        state.setValue("45.03");

        new Dot(state).execute();

        assertEquals("45.03", state.getValue());
    }
}
