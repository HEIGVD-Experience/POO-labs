package calculator.operator;

import calculator.State;

/**
 * Push the value onto the operands stack then sets the current value to 0
 */
public class Enter extends Operator {
    public Enter(State state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.getIsError()) {
          return;
        }

        state.getOperands().push(Double.parseDouble(state.getValue()));
        state.setValue("0");
    }
}
