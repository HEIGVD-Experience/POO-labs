package org.example;

import org.example.matrix.Matrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    @Test
    public void differentModulusAdd() {
        Matrix m1 = new Matrix(4, 4, 5);
        Matrix m2 = new Matrix(4, 4, 7);

        assertThrows(RuntimeException.class, () -> m1.add(m2));
    }

    @Test
    public void sameModulusAddDoesNotThrow() {
        Matrix m1 = new Matrix(4, 4, 7);
        Matrix m2 = new Matrix(4, 4, 7);

        assertDoesNotThrow(() -> m1.add(m2));
    }

    @Test
    public void differentModulusSub() {
        Matrix m1 = new Matrix(4, 4, 5);
        Matrix m2 = new Matrix(4, 4, 7);

        assertThrows(RuntimeException.class, () -> m1.sub(m2));
    }

    @Test
    public void sameModulusSubDoesNotThrow() {
        Matrix m1 = new Matrix(4, 4, 7);
        Matrix m2 = new Matrix(4, 4, 7);

        assertDoesNotThrow(() -> m1.sub(m2));
    }

    @Test
    public void differentModulusMul() {
        Matrix m1 = new Matrix(4, 4, 5);
        Matrix m2 = new Matrix(4, 4, 7);

        assertThrows(RuntimeException.class, () -> m1.mul(m2));
    }

    @Test
    public void sameModulusMulDoesNotThrow() {
        Matrix m1 = new Matrix(4, 4, 7);
        Matrix m2 = new Matrix(4, 4, 7);

        assertDoesNotThrow(() -> m1.mul(m2));
    }

    @Test
    public void printMatrix() {
        int[][] values = new int[][] {
                new int[] {1, 2, 3},
                new int[] {4, 5, 6},
                new int[] {7, 8, 9},
        };

        Matrix m = new Matrix(values, 9);

        assertEquals("""
        1 2 3\s
        4 5 6\s
        7 8 9\s
        """, m.toString());
    }

    @Test
    public void givenValuesCorrectlySetsWidth() {
        int[][] values = new int[][] {
                new int[] {1, 2, 3},
                new int[] {4, 5, 6},
                new int[] {7, 8, 9},
                new int[] {10, 11, 12},
        };

        Matrix m = new Matrix(values, 8);

        assertEquals(3, m.getWidth());
    }

    @Test
    public void givenValuesCorrectlySetsHeight() {
        int[][] values = new int[][] {
                new int[] {1, 2, 3},
                new int[] {4, 5, 6},
                new int[] {7, 8, 9},
                new int[] {10, 11, 12},
        };

        Matrix m = new Matrix(values, 8);

        assertEquals(4, m.getHeight());
    }

    @Test
    public void randomMatrixWithModulusZeroThrows() {
        assertThrows(RuntimeException.class, () -> new Matrix(4, 3, 0));
    }

    @Test
    public void randomMatrixHasCorrectWidth() {
        Matrix m = new Matrix(4, 3, 9);

        assertEquals(3, m.getWidth());
    }

    @Test
    public void randomMatrixHasCorrectHeight() {
        Matrix m = new Matrix(4, 3, 9);

        assertEquals(4, m.getHeight());
    }

    @Test
    public void valuesMatrixWithModulusZeroThrows() {
        int[][] values = new int[][] {
                new int[] {1, 2, 3},
                new int[] {4, 5, 6},
                new int[] {7, 8, 9},
        };

        assertThrows(RuntimeException.class, () -> new Matrix(values, 0));
    }
}
