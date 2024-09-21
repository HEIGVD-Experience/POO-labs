package org.example.matrix;

import org.example.matrix.operators.Addition;
import org.example.matrix.operators.Multiplication;
import org.example.matrix.operators.Operator;
import org.example.matrix.operators.Substraction;

public class Matrix {
    private final int width;

    private final int height;

    private final int[][] values;

    private final int modulus;

    public Matrix(int height, int width, int modulus) {
        checkModulus(modulus);

        this.width = width;
        this.height = height;
        this.modulus = modulus;

        values = new int[this.height][this.width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                // Use the modulus to have the random number range between [0, modulus[
                values[i][j] = (int) (Math.random() * modulus);
            }
        }
    }

    /**
     * Create a new matrix filled with `values`, the user of this API is responsible for checking that the provided
     * values are valid in the context of this new matrix.
     *
     * @param values The values that will be inserted in the resulting matrix
     * @param modulus The modulus of the matrix
     */
    public Matrix(int[][] values, int modulus) {
        checkModulus(modulus);

        this.height = values.length;
        this.width = height == 0 ? 0 : values[0].length;
        this.modulus = modulus;
        this.values = values;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Either return the value at (x,y) or 0 if out of bounds
     *
     * @param y The y position
     * @param x The x position
     * @return the value at (x,y) or 0 if out of bounds
     */
    protected int at(int y, int x) {
        if (y > height - 1 || x > width - 1) {
            return 0;
        }

        return values[y][x];
    }

    private void checkModulus(int modulus) {
        if (modulus <= 0) {
            throw new RuntimeException();
        }
    }

    /**
     * @throws RuntimeException When modulus are not equal
     */
    private Matrix applyOperator(Matrix other, Operator op) throws RuntimeException {
        if (modulus != other.modulus) {
            throw new RuntimeException("Matrices do not have the same modulus");
        }

        int[][] values = new int[Math.max(height, other.height)][Math.max(width, other.width)];

        for (int y = 0; y < Math.max(height, other.height); ++y) {
            for (int x = 0; x < Math.max(width, other.width); ++x) {
                values[y][x] = Math.floorMod(op.apply(at(y, x), other.at(y, x)), modulus);
            }   
        }

        return new Matrix(values, modulus);
    }

    public Matrix add(Matrix other) {
        return applyOperator(other, new Addition());
    }

    public Matrix sub(Matrix other) {
        return applyOperator(other, new Substraction());
    }

    public Matrix mul(Matrix other) {
        return applyOperator(other, new Multiplication());
    }

    @Override
    public String toString() {
        String s = "";

        // We calculate the number of characters a single cell takes in order to keep things aligned
        int whitespace = (int) Math.ceil(Math.log10(modulus)) + 1;

        for (int[] i : values) {
            for (int j : i) {
                s += String.format("%-" + whitespace + "d", j);
            }

            s += "\n";
        }

        return s;
    }
}
