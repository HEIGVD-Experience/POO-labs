package calculator.operator;

import calculator.State;

public class PushOperand extends Operator {
    private final double value;

    public PushOperand(State state, double value) {
        super(state);
        this.value = value;
    }

    @Override
    public void execute() {
        state.getOperands().push(value);
    }
}
