package calculator.operator;

import calculator.State;

public class MemoryRecall extends Operator {
    public MemoryRecall(State state) {
        super(state);
    }

    @Override
    public void execute() {
        state.setValue(Double.toString(state.getMemory()));
    }
}
