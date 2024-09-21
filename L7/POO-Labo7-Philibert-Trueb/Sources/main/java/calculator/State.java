package calculator;

import util.Stack;


/**
 * @author Philibert Alexandre, Trüeb Guillaume
 *
 * Represents the state of a calculator and provides ways to interact with it.
 */
public class State {

    /**
     * The current value of the calculator
     */
    private String value;

    private boolean isError;

    private final Stack<Double> operands;

    private double memory;

    private boolean isResult;

    public State() {
        operands = new Stack<>();
        value = "0";
        isResult = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public boolean getIsError() {
        return isError;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public double getMemory() {
        return memory;
    }

    public boolean getIsResult() {
        return isResult;
    }

    public void setIsResult(boolean isResult) {
        this.isResult = isResult;
    }

    public Stack<Double> getOperands() {
        return operands;
    }

    @Override
    public String toString() {
        return value.isEmpty() ? "0" : value;
    }
}
