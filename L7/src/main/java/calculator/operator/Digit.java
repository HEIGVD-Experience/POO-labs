package calculator.operator;

import calculator.State;

public class Digit extends Operator {

    private final int value;

    public Digit(State state, int value) {
        super(state);
        this.value = value;
    }

    @Override
    public void execute() {
        String value = state.getValue();

        if (value.equals("0")) {
            value = "";
        } else if (value.equals("-0")) {
            value = "-";
        }

        if (state.getIsResult()) {
            state.getOperands().push(Double.parseDouble(state.getValue()));
            state.setIsResult(false);
            value = Integer.toString(this.value);
        } else {
            value += this.value;
        }


        state.setValue(value);
    }
}
