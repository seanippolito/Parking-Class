package com.company.main;

import org.junit.jupiter.api.Test;

import static com.company.main.Main.numSequence;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void numSequenceTest() {
        assertEquals("0", numSequence(-5));
        assertEquals("0", numSequence(0));
        assertEquals("1", numSequence(1));
        assertEquals("11", numSequence(2));
        assertEquals("21", numSequence(3));
        assertEquals("1211", numSequence(4));
        assertEquals("111221", numSequence(5));
        assertEquals("312211", numSequence(6));
        assertEquals("13112221", numSequence(7));
        assertEquals("1113213211", numSequence(8));
        assertEquals("31131211131221", numSequence(9));
        assertEquals("13211311123113112211", numSequence(10));
        assertEquals("11131221133112132113212221", numSequence(11));
    }

}