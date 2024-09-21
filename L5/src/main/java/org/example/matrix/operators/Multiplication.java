package org.example.matrix.operators;

public class Multiplication extends Operator {
    @Override
    public int apply(int left, int right) {
        return left * right;
    }
}
