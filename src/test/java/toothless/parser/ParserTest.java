package toothless.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void validateEventInput_normalInput_success() throws Exception {
        // /from is before /to
        assertArrayEquals(new String[]{"dinner", "2024-02-02 18:00", "2024-02-02 20:30"},
                new Parser().parseEventInput("event dinner /from 2024-02-02 18:00 /to 2024-02-02 20:30"));

        // /to is before /from
        assertArrayEquals(new String[]{"party", "2024-02-02 21:00", "2024-02-02 23:59"},
                new Parser().parseEventInput("event party /to 2024-02-02 23:59 /from 2024-02-02 21:00"));

        // extra whitespace
        assertArrayEquals(new String[]{"dinner", "2024-02-02 18:00", "2024-02-02 20:30"},
                new Parser().parseEventInput("event   dinner   /from    2024-02-02 18:00   /to   2024-02-02 20:30"));
    }

    @Test
    public void validateEventInput_emptyInput_exceptionThrown() {
        try {
            assertArrayEquals(new String[]{}, new Parser().parseEventInput("event"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_emptyDescription_exceptionThrown() {
        try {
            assertArrayEquals(new String[]{null, "2024-02-02 18:00", "2024-02-02 20:30"},
                    new Parser().parseEventInput("event  /from 2024-02-02 18:00 /to 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_emptyFrom_exceptionThrown() {
        try {
            assertArrayEquals(new String[]{"dinner", null, "2024-02-02 20:30"},
                    new Parser().parseEventInput("event dinner /from    /to 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_emptyTo_exceptionThrown() {
        try {
            assertArrayEquals(new String[]{"dinner", "2024-02-02 18:00", null},
                    new Parser().parseEventInput("event dinner /from 2024-02-02 18:00 /to    "));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_doubleFrom_exceptionThrown() {
        try {
            assertArrayEquals(new String[]{"dinner", "2024-02-02 18:00", null},
                    new Parser().parseEventInput("event dinner /from 2024-02-02 18:00 /from 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease remember to include the /from and /to fields.", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_extraFields_exceptionThrown() {
        try {
            assertArrayEquals(new String[]{"dinner", "2024-02-02 18:00", null},
                    new Parser().parseEventInput("event dinner /from 2024-02-02 18:00"
                            + " /from 2024-02-02 20:30 /to 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]", e.getMessage());
        }
    }
}
