package com.examples.Testing.Challenges;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class UtilitiesTest {
    private Utilities utilities;
    private String input;
    private String expected;

    public UtilitiesTest(String input,String expected) {
        this.input=input;
        this.expected=expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList( new Object[][]{
                {"ABCDEFF","ABCDEF"},
                {"AB88EFFG","AB8EFG"},
                {"112233445566","123456"},
                {"ZYZQQB","ZYZQB"},
                {"A","A"}
        });
    }

    @Before
    public void setUp() throws Exception {
        utilities = new Utilities();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Completed testing...");
    }

    @Test
    public void everyNthChar() {
        char[] arr=new char[]{'h','e','l','l','o'};
        assertArrayEquals(new char[]{'e', 'l'},utilities.everyNthChar(arr,2));
        assertArrayEquals(new char[]{'h','e','l','l','o'},utilities.everyNthChar(arr,6));
    }

    @Test
    public void removePairs() {
//        assertEquals("ABCDEF", utilities.removePairs("AABCDDEFF"));
//        assertEquals("ABCADEF", utilities.removePairs("AABCAADDEFF"));
//        assertEquals("A", utilities.removePairs("A"));
//        assertNull("Argument passed was null, but returned value is not null", utilities.removePairs(null));
//        assertEquals("", utilities.removePairs(""));

        assertEquals(expected,utilities.removePairs(input));
    }

    @Test(expected = ArithmeticException.class)
    public void converter() throws Exception {
        assertEquals(300,utilities.converter(10,5));
        utilities.converter(10,0);
    }

    @Test
    public void nullIfOddlength() {
        assertNull(utilities.nullIfOddlength("oddlength"));
        assertNotNull(utilities.nullIfOddlength("evenlength"));
    }
}