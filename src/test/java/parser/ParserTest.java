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
}
