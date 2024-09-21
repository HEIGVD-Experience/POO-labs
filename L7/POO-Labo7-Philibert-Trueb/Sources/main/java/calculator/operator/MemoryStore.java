package calculator.operator;

import calculator.State;

public class MemoryStore extends Operator {
    public MemoryStore(State state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.getIsError()) {
            return;
        }

        state.setMemory(Double.parseDouble(state.getValue()));
    }
}
