package dude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void isNumeric_nonInteger_falseReturned() {
        assertEquals(false, Parser.isNumeric("a"));
    }

    @Test
    public void isNumeric_integer_trueReturned() {
        assertEquals(true, Parser.isNumeric("1234"));
    }

    @Test
    public void isDate_validDate_trueReturned() {
        assertEquals(true, Parser.isDate("2022-01-01"));
    }

    @Test
    public void isDate_invalidDate_falseReturned() {
        assertEquals(false, Parser.isDate("2022-13-01"));
        assertEquals(false, Parser.isDate("2022-1-01"));
        assertEquals(false, Parser.isDate("2022-01-32"));
        assertEquals(false, Parser.isDate("2022-02-30"));
        assertEquals(false, Parser.isDate("202-01-01"));
    }
}
