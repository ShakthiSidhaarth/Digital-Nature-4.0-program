package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void add() {
        Calculator calculator = new Calculator();

        assertEquals( 10, calculator.add(5,5));
    }
}