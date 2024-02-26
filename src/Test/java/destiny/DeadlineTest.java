package destiny;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getBy_validInput_properDates() throws DestinyException {
        assertEquals("29/10/2024 1234", new Deadline("test",
                "29/10/2024 1234").getBy());
    }

    @Test
    public void getString_validInput_properString() throws DestinyException {
        assertEquals("[D][ ] test (by: Dec 12 2024 12:34PM)",
                new Deadline("test", "12/12/2024 1234").toString());
    }

    @Test
    public void getBy_invalidInput_exceptionThrown() {
        try {
            assertEquals("29/10/2024 1234", new Deadline("test",
                    "33/13/2024 4567").getBy());
        } catch (DestinyException e) {
            assertEquals("Something went wrong...\n"
                    + "Please enter the date and time in the following format:\n"
                    + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234", e.getMessage());
        }
        try {
            assertEquals("29/10/2024 1234", new Deadline("test",
                    "12/12/2024").getBy());
        } catch (DestinyException e) {
            assertEquals("Something went wrong...\n"
                    + "Please enter the date and time in the following format:\n"
                    + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234", e.getMessage());
        }
    }
}
