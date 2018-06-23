package com.startandroid.fibapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final String REGEX_INDEX = "^[0-9]\\d*$";

    public static boolean checkEnteredStringByRegex(final String ENTERED_STRING) {
        final Pattern pattern = Pattern.compile(REGEX_INDEX);
        final Matcher matcher = pattern.matcher(ENTERED_STRING);
        return matcher.matches();
    }
}
