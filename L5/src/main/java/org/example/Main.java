package org.example;

import org.example.matrix.Matrix;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        if (args.length != 5) {
            throw new RuntimeException("5 arguments are required");
        }

        int n1, m1, n2, m2, modulus;

        try {
            n1 = Integer.parseInt(args[0]);
            m1 = Integer.parseInt(args[1]);
            n2 = Integer.parseInt(args[2]);
            m2 = Integer.parseInt(args[3]);
            modulus = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Could not parse arguments as integers");
        }

        Matrix matrix1 = new Matrix(m1, n1, modulus);
        Matrix matrix2 = new Matrix(m2, n2, modulus);

        System.out.println("The modulus is " + modulus + "\n");

        System.out.println("one");
        System.out.println(matrix1);

        System.out.println("two");
        System.out.println(matrix2);

        System.out.println("one + two");
        System.out.println(matrix1.add(matrix2));

        System.out.println("one - two");
        System.out.println(matrix1.sub(matrix2));

        System.out.println("one x two");
        System.out.println(matrix1.mul(matrix2));

    }
}