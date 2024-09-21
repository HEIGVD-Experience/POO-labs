package calculator.operator;

import calculator.State;

public class Clear extends ClearError {
    public Clear(State state) {
        super(state);
    }

    @Override
    public void execute() {
        super.execute();

        state.getOperands().clear();
    }
}
