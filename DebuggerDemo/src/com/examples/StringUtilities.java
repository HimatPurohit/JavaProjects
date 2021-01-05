package com.examples;

public class StringUtilities {
    private StringBuilder stringBuilder = new StringBuilder();
    private int charsAdded = 0;

    public void add(StringBuilder stringBuilder, char c) {
        this.stringBuilder.append(c);
        charsAdded++;
    }
}
