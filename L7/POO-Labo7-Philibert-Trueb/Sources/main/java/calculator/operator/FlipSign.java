package calculator.operator;

import calculator.State;

/**
 * Flips the sign of the current value
 */
public class FlipSign extends Operator {
    public FlipSign(State state) {
        super(state);
    }

    @Override
    public void execute() {
        String value = state.getValue();

        if (!value.isEmpty() && value.charAt(0) == '-') {
            state.setValue(value.substring(1));
        } else {
           if (value.isEmpty()) {
               value += 0;
           }
            state.setValue("-" + value);
        }
    }
}
