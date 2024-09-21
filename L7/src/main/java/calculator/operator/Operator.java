package calculator.operator;

import calculator.State;

/**
 * The Operator class provides a way to interact with a calculator state
 */
public abstract class Operator
{
    protected final State state;

    public Operator(State state) {
        this.state = state;
    }

    public abstract void execute();
}