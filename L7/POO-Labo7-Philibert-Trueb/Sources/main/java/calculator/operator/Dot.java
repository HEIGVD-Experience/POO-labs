package calculator.operator;

import calculator.State;

/**
 * Adds a dot to the state value only if none is present
 */
public class Dot extends Operator {
    public Dot(State state) {
        super(state);
    }

    @Override
    public void execute() {
        String value = state.getValue();

        if (!value.contains(".")) {
            // Double.parseDouble needs a "0" in front of the ".", this helps make the parsing easier
            if (value.isEmpty()) {
                value += "0";
            }

            state.setValue(value + '.');
        }
    }
}
