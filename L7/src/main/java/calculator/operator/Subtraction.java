package calculator.operator;

import calculator.State;
import util.Stack;

public class Subtraction extends ResultOperator {
    public Subtraction(State state) {
        super(state);
    }

    public void execute() {
        if (state.getIsError() || state.getOperands().isEmpty()) {
            return;
        }

        super.execute();

        double value = Double.parseDouble(state.getValue());
        Stack<Double> operands = state.getOperands();

        state.setValue(Double.toString(value - operands.pop()));
    }
}
