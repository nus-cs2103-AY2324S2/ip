package drake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseDeadlineSuccessTest() throws Exception {
        String testInput = "deadline eat dinner /by 2024-12-01";
        Object[] actualOutput = Parser.parseDeadline(testInput);
        Object[] expectedOutput = {"eat dinner", LocalDate.parse("2024-12-01",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()};

        assertEquals(expectedOutput[0], actualOutput[0]);
        assertEquals(expectedOutput[1], actualOutput[1]);
    }

    @Test
    public void parseDeadlineWrongDateFormatTest() {
        try {

            String testInput = "deadline eat dinner /by 2024-14-01";
            Object[] actualOutput = Parser.parseDeadline(testInput);
            Object[] expectedWrongOutput = {"eat dinner", LocalDate.parse("2024-14-01",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()};
            assertEquals(expectedWrongOutput[0], actualOutput[0]);
            assertEquals(expectedWrongOutput[1], actualOutput[1]);
            fail(); //This block should not be run.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Date is of the wrong format!", e.getMessage());
        }
    }

    @Test
    public void parseDeadlineWrongSpellingFormatTest() {
        try {
            String testInput = "dedline eat dinner /by 2024-12-01";

            Parser.parseDeadline(testInput);
            fail(); //This block should not be run.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Looks like you spelt deadline wrong. Please try again!", e.getMessage());
        }
    }

    @Test
    public void parseDeadlineNoByTest() {
        try {
            String testInput = "deadline eat dinner 2024-12-01";

            Parser.parseDeadline(testInput);
            fail(); //This block should not be run.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Looks like you didn't input the 'by' sub-command. "
                    + "This isn't allowed!", e.getMessage());
        }
    }
}
