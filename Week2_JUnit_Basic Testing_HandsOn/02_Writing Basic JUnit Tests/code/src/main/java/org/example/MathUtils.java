package org.example;

public class MathUtils {

    public int square(int number) {
        return number * number;
    }

    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}
