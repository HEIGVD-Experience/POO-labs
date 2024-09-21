package calculator.operator;

import calculator.State;

public class Backspace extends Operator {
    public Backspace(State state) {
        super(state);
    }

    @Override
    public void execute() {
        String value = state.getValue();

        if (!value.isEmpty()) {
            String newValue = value.substring(0, value.length() - 1);

            if (newValue.isEmpty()) {
                newValue = "0";
            }

            state.setValue(newValue);
        }
    }
}
