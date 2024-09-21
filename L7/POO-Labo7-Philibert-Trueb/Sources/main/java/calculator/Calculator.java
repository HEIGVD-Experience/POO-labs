package calculator;

import calculator.operator.*;

import java.util.Scanner;

/**
 * @author Philibert Alexandre, Trüeb Guillaume
 *
 * Provides a basic calculator on the standard output.
 */
public class Calculator {

    private final State state;

    private final Scanner scanner;

    public Calculator() {
        state = new State();
        scanner = new Scanner(System.in);
    }

    /**
     * Starts the calculator
     */
    public void run() {
        Operator operator;

        do {
            System.out.print("> ");

            operator = parseOperator(scanner.nextLine().strip());

            if (operator != null) {
                eval(operator);
            } else {
                System.out.println("# unknown operation #");
            }

            if (state.getIsError()) {
                System.out.println("# error #");
            }

            System.out.println(state.getOperands());
        } while (!(operator instanceof Exit));
    }

    /**
     * Eval part of the REPL
     * @param operator The operator to execute
     */
    private void eval(Operator operator) {
        if (!(operator instanceof PushOperand)) {
            state.setValue(Double.toString(state.getOperands().pop()));
        }

        operator.execute();

        if (state.getIsResult()) {
            state.getOperands().push(Double.parseDouble(state.getValue()));
            state.setIsResult(false);
        }
    }

    /**
     * Returns an Operator to execute given an input string.
     *
     * @param input The string from which to derive the operator to execute
     * @return The operator derived from the input string
     */
    private Operator parseOperator(String input) {
        return switch(input) {
            case "+" -> new Addition(state);
            case "-" -> new Subtraction(state);
            case "*" -> new Multiplication(state);
            case "/" -> new Division(state);
            case "square" -> new Power(state, 2);
            case "sqrt" -> new SquareRoot(state);
            case "clear" -> new Clear(state);
            case "clearerr" -> new ClearError(state);
            case "ms" -> new MemoryStore(state);
            case "mr" -> new MemoryRecall(state);
            case "flip" -> new FlipSign(state);
            case "invert" -> new Invert(state);
            case "exit" -> new Exit(state);
            default -> tryParseInput(input);
        };
    }

    /**
     * Tries to parse the input as a double, if it fails return null.
     */
    private Operator tryParseInput(String input) {
        try {
            return new PushOperand(state, Double.parseDouble(input));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * This operator should be called when exiting the calculator, it provides a place to handle logic when exiting
     * the calculator.
     */
    private static class Exit extends Clear {
        public Exit(State state) {
            super(state);
        }

        @Override
        public void execute() {}
    }
}
