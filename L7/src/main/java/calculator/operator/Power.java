package calculator.operator;

import calculator.State;

public class Power extends ResultOperator {

    private final int value;

    public Power(State state, int value) {
        super(state);
        this.value = value;
    }

    @Override
    public void execute() {
        if (state.getIsError()) {
            return;
        }

        super.execute();

        double value = Double.parseDouble(state.getValue());
        state.setValue(Double.toString(Math.pow(value, this.value)));
    }
}
