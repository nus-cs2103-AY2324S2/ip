package drake;

import org.junit.jupiter.api.Test;

import drake.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class ParserTest {
    @Test
    public void parseDeadlineSucessTest() throws Exception {
        String testInput = "deadline eat dinner /by 2024-12-01";
        Object[] actualOutput = Parser.parseDeadline(testInput);
        Object[] expectedOutput = {"eat dinner", LocalDate.parse("2024-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()};
        assertEquals(actualOutput, actualOutput);
    }

    @Test
    public void parseDeadlineWrongDateFormatTest() throws Exception {
        try {

            String testInput = "deadline eat dinner /by 2024-14-01";
            Object[] actualOutput = Parser.parseDeadline(testInput);
            Object[] expectedWrongOutput = {"eat dinner", LocalDate.parse("2024-14-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()};
        
            assertEquals(actualOutput, expectedWrongOutput);
            fail(); //This block should not be run.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Date is of the wrong format!", e.getMessage());
        }
    }
    @Test
    public void parseDeadlineWrongSpellingFormatTest() throws Exception {
        try {

            String testInput = "dedline eat dinner /by 2024-12-01";
            Object[] actualOutput = Parser.parseDeadline(testInput);
            Object[] expectedWrongOutput = {"eat dinner", LocalDate.parse("2024-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()};
            fail(); //This block should not be run.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Looks like you spelt deadline wrong. Please try again!", e.getMessage());
        }
    }
}