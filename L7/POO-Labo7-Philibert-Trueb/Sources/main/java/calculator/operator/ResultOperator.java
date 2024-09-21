package calculator.operator;

import calculator.State;

/**
 * The result operator puts the state in the result mode.
 * This class should be used for all operators that are expected to push the computed value into the stack once an
 * operand is executed.
 */
public abstract class ResultOperator extends Operator {
    public ResultOperator(State state) {
        super(state);
    }

    @Override
    public void execute() {
        state.setIsResult(true);
    }
}
