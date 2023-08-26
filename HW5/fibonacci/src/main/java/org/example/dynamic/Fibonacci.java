package org.example.dynamic;

import java.util.Scanner;

public class Fibonacci {
    public static long dynamicFibonacci(int number) {
        if (number <= 0) {
            return 0;
        }

        long[] fib = new long[number + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= number ; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[number];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write number: ");
        int number = scanner.nextInt();
        long result = dynamicFibonacci(number);
        System.out.println("Fibonacci (" + number + ") = " + result);
    }
}
