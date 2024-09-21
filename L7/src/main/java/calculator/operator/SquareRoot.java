package calculator.operator;

import calculator.State;

public class SquareRoot extends ResultOperator {
    public SquareRoot(State state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.getIsError()) {
            return;
        }

        double value = Double.parseDouble(state.getValue());

        if (value < 0) {
            state.setIsError(true);
            return;
        }

        super.execute();

        state.setValue(Double.toString(Math.sqrt(value)));
    }
}
