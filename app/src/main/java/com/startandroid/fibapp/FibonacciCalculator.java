package com.startandroid.fibapp;

import android.util.Log;

import java.math.BigInteger;

public final class FibonacciCalculator {

    public static BigInteger calculateFibonacciNumberByIndex(final int FIBONACCI_INDEX, final Getter<Boolean> isLiving) {

        if (FIBONACCI_INDEX == 0) {
            return BigInteger.ZERO;
        }
        final BigInteger[][] matrix = {
                {BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}
        };
        final BigInteger[][] powerOfMatrix = matrixPowerFast(matrix, FIBONACCI_INDEX - 1, isLiving);
        if (powerOfMatrix == null || !isLiving.get()) {
            return null;
        }
        return powerOfMatrix[0][0];
    }

    private static BigInteger[][] matrixPowerFast(final BigInteger[][] matrix, final int POW, final Getter<Boolean> isLiving) {
        if (matrix != null && isLiving.get()) {
            if (POW == 0) {
                return new BigInteger[][]{
                        {BigInteger.ONE, BigInteger.ZERO},
                        {BigInteger.ZERO, BigInteger.ONE}
                };
            } else if (POW % 2 == 0) {
                final BigInteger[][] matrixMultiplicationBigInteger = matrixMultiplication(matrix, matrix, isLiving);
                if (matrixMultiplicationBigInteger == null || !isLiving.get()) {
                    return null;
                }
                final BigInteger[][] matrixPowerFastBigInteger = matrixPowerFast(matrixMultiplicationBigInteger, POW / 2, isLiving);
                if (matrixPowerFastBigInteger == null || !isLiving.get()) {
                    return null;
                }
                return matrixPowerFastBigInteger;
            } else {
                final BigInteger[][] matrixPowerFastBigInteger = matrixPowerFast(matrix, POW - 1, isLiving);
                if (matrixPowerFastBigInteger == null && !isLiving.get()) {
                    return null;
                }
                final BigInteger[][] matrixMultiplicationBigInteger = matrixMultiplication(matrixPowerFastBigInteger, matrix, isLiving);
                if (matrixMultiplicationBigInteger == null || !isLiving.get()) {
                    return null;
                }
                return matrixMultiplicationBigInteger;
            }
        } else {
            return null;
        }
    }

    private static BigInteger[][] matrixMultiplication(final BigInteger[][] firstMatrix, final BigInteger[][] secondMatrix, final Getter<Boolean> isLiving) {
        if (firstMatrix != null && secondMatrix != null && isLiving.get()) {
            return new BigInteger[][]{
                    {firstMatrix[0][0].multiply(secondMatrix[0][0]).add(firstMatrix[0][1].multiply(secondMatrix[1][0])), firstMatrix[0][0].multiply(secondMatrix[0][1]).add(firstMatrix[0][1].multiply(secondMatrix[1][1]))},
                    {firstMatrix[1][0].multiply(secondMatrix[0][0]).add(firstMatrix[1][1].multiply(secondMatrix[1][0])), firstMatrix[1][0].multiply(secondMatrix[0][1]).add(firstMatrix[1][1].multiply(secondMatrix[1][1]))},
            };
        } else {
            return null;
        }
    }
}
