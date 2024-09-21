package calculator.operator;

import calculator.State;

/**
 * Inverts the current value such that x becomes 1/x
 */
public class Invert extends Operator {
    public Invert(State state) {
        super(state);
    }

    @Override
    public void execute() {
        double value = Double.parseDouble(state.getValue());

        if (value == 0) {
            state.setIsError(true);
            return;
        }

        state.setValue(Double.toString(1/value));
    }
}
