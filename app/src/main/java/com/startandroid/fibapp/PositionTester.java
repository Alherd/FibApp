package com.startandroid.fibapp;

import android.support.annotation.Nullable;

import java.math.BigInteger;

import static com.startandroid.fibapp.ErrorType.NOT_A_NUMBER;
import static com.startandroid.fibapp.ErrorType.TOO_BIG_NUMBER;

public final class PositionTester {
    public int getStringRecourseByEnteredString(final String POSITION) {
        final ErrorType errorType = getErrorTypeByTestingPosition(POSITION);
        if (errorType != null) {
            switch (errorType) {
                case NOT_A_NUMBER:
                    return R.string.incorrect_input;
                case TOO_BIG_NUMBER:
                    return R.string.too_large_number;
                default:
                    return R.string.unknown_error;
            }
        }
        return R.string.no_error;
    }

    @Nullable
    private ErrorType getErrorTypeByTestingPosition(final String POSITION) {
        if (!StringUtils.checkEnteredStringByRegex(POSITION)) {
            return NOT_A_NUMBER;
        } else {
            final BigInteger positionBigInteger = new BigInteger(POSITION);
            if (positionBigInteger.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) == -1) {
                return null;
            } else {
                return TOO_BIG_NUMBER;
            }
        }
    }
}
