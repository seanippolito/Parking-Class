package com.seanippolito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumSequenceTest {
    public static String numSequence(int n) {
        StringBuilder sb = new StringBuilder();
        if(n < 1) { return "0";}
        if(n == 1) {
            return Integer.toString(n);
        }
        sb.append("1");
        for(int i = 2; i <= n; i++){
            int count = 1;
            char prev_char = sb.charAt(0);
            int start = 0;
            for(int j = 1; j < sb.length(); j++){
                if(prev_char == sb.charAt(j)){
                    count++;
                } else {
                    char temp = sb.charAt(j);
                    int oldLen = sb.length();
                    sb.replace(start, j, Integer.toString(count) + prev_char); //121
                    int newLen = sb.length();
                    j = j + newLen - oldLen;
                    count = 1;
                    start = j;
                    prev_char = temp;
                }
            }
            sb.replace(start, sb.length(), Integer.toString(count) + prev_char);
        }
        return sb.toString();
    }

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