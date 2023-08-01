package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestCalculator {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() {
        calculator = null;
    }
    @Test
    public void testAdd() {
        //Given
        int a = 2;
        int b = 3;

        //When
        int result = calculator.add(a, b);

        //Then
        int expected = 5;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSubtract() {
        //Given
        int a = 5;
        int b = 3;

        //When
        int result = calculator.subtract(a, b);


        //Then
        int expected = 2;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMultiply() {
        //Given
        int a = 5;
        int b = 3;

        //When
        int result = calculator.multiply(a, b);


        //Then
        int expected = 15;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDivide() {
        //Given
        int a = 6;
        int b = 3;

        //When
        int result = calculator.divide(a, b);


        //Then
        int expected = 2;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
    }
}
