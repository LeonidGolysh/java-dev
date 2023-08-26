package org.example.recursive;

import java.util.Scanner;

public class Fibonacci {
    public static long recursiveFibonacci(int number) {
        if (number <= 1) {
            return number;
        }
        return recursiveFibonacci(number - 1) + recursiveFibonacci(number - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write number: ");
        int number = scanner.nextInt();
        long result = recursiveFibonacci(number);
        System.out.println("Fibonacci(" + number + ") = " + result);
    }
}
