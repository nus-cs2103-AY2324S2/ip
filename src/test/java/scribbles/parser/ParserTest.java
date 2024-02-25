package scribbles.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void isMissingDeadlineInformationTest() {

        // test case 1
        Parser p1 = new Parser("deadline homework1 01/01/2024 2359");
        assertEquals(true, p1.isMissingDeadlineInformation());

        // test case 2
        Parser p2 = new Parser("deadline /by 01/01/2024 2359");
        assertThrows(IndexOutOfBoundsException.class, () -> {
                    p2.isMissingDeadlineInformation();
                });

        // test case 3
        Parser p3 = new Parser("deadline homework3 /by ");
        assertEquals(true, p3.isMissingDeadlineInformation());

        // test case 4
        Parser p4 = new Parser("deadline homework4 /by 01/01/2024 2359");
        assertEquals(false, p4.isMissingDeadlineInformation());
    }

    @Test
    public void isInvalidEventTest() {

        // test case 1: missing /from and /to and other information
        Parser p1 = new Parser("event event1");
        assertEquals(true, p1.isInvalidEvent());

        // test case 2: missing /from
        Parser p2 = new Parser("event event2 01/01/2024 1230 /to 01/01/2024 1300");
        assertEquals(true, p2.isInvalidEvent());

        // test case 3: missing /to
        Parser p3 = new Parser("event event3 /from 01/01/2024 1230 01/01/2024 1300");
        assertEquals(true, p3.isInvalidEvent());

        // test case 5: missing end date/time
        Parser p5 = new Parser("event event3 /from 01/01/2024 2359 /to ");
        assertEquals(true, p5.isInvalidEvent());

        // test case 6: correct information given
        Parser p6 = new Parser("event event3 /from 01/01/2024 2359 /to  01/01/2024 2359");
        assertEquals(false, p6.isInvalidEvent());

        // test case 7: /to before /from
        Parser p7 = new Parser("event event3 /to 01/01/2024 2023 /from 12/12/2024 2359");
        assertEquals(true, p7.isInvalidEvent());
    }
}
