package calculator.operator;

import calculator.State;

import java.util.Objects;


public class Zero extends Digit {
    public Zero(State state) {
        super(state, 0);
    }

    @Override
    public void execute() {
        String value = state.getValue();

        if (!Objects.equals(value, "") && Double.parseDouble(value) != 0.0) {
            super.execute();
        }
    }
}
