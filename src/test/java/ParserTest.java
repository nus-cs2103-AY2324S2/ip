package oop;

import lemona.exceptions.MissingDescriptionException;
import lemona.oop.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseDates_givenString_success() {
        try {
            String input = "deadline take lemona /by 01/11/2022 2359";
            String[] output = Parser.parseDates(Parser.trim(input));
            String[] expectedOutput = {"deadline", "take lemona ","01/11/2022 2359"};
            assertEquals(expectedOutput[0], output[0]);
            assertEquals(expectedOutput[1], output[1]);
            assertEquals(expectedOutput[2], output[2]);
        } catch (MissingDescriptionException e) {
        }
    }

    @Test
    void parseDates_missingDescription_exceptionThrown() {
        String input = "deadline /by 01/11/2022 2359";
        try {
            Parser.parseDates(Parser.trim(input));
        } catch (MissingDescriptionException e) {
            String expectedOutput = "I think you haven't had enough vitamin K." +
                    "\nYour input should be in format of:"
                    + "\n\t{ deadline (Task) (/by DueDate) }"
                    + "\nI suggest you take some LEMONA.";
            assertEquals(expectedOutput, e.toString(Parser.trim(input)[0]));
        }
    }
}
