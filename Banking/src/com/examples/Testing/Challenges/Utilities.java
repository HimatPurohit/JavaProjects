package com.examples.Testing.Challenges;

public class Utilities {

    // returns an array containing every nth char
    public char[] everyNthChar(char[] sourceArray, int n) {
        if (sourceArray == null || sourceArray.length < n) {
            return sourceArray;
        }
        int returnedLength = sourceArray.length / n;
        char[] result = new char[returnedLength];
        int index = 0;
        for (int i = n - 1; i < sourceArray.length; i += n) {
            result[index++] = sourceArray[i];
        }
        return result;
    }

    // Removes Pairs of the same char by just keeping single character
    // ABBCCCDDE -> ABCDE
    // ABBCBDEF -> ABCBDEF
    public String removePairs(String source) {
        if (source == null || source.length() < 2) {
            return source;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] string = source.toCharArray();

        stringBuilder.append(string[0]);
        for (int i = 1; i < string.length; i++) {
            if (string[i] != string[i - 1]) {
                stringBuilder.append(string[i]);
            }
        }
        return stringBuilder.toString();
    }

    // Performs a conversion based on some internal business rule
    public int converter(int a, int b) {
        return (a / b) + (a * 30) - 2;
    }

    public String nullIfOddlength(String source) {
        if (source.length() % 2 == 0) {
            return source;
        }
        return null;
    }
}
