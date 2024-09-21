package calculator.operator;

import calculator.State;
import util.Stack;

public class Division extends ResultOperator {
    public Division(State state) {
        super(state);
    }

    public void execute() {
        if (state.getIsError()) {
            return;
        }

        if (state.getOperands().isEmpty()) {
            state.setIsError(true);
            return;
        }

        super.execute();

        double value = Double.parseDouble(state.getValue());
        Stack<Double> operands = state.getOperands();

        state.setValue(Double.toString(operands.pop() / value));
    }
}
