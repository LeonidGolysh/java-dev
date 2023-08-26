package org.example.iterative;

import java.util.Scanner;

public class Fibonacci {
    public static long iterativeFibonacci(int number) {
        if (number <= 1) {
            return number;
        }

        long fib1 = 0;
        long fib2 = 1;
        long fib = 0;

        for (int i = 2; i <= number ; i++) {
            fib = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib;
        }
        return fib;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write number: ");
        int number = scanner.nextInt();
        long result = iterativeFibonacci(number);
        System.out.println("Fibonacci (" + number + ") = " + result);
    }
}
