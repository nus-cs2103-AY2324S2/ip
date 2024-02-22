package destiny;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getFrom_validInput_properDates() throws DestinyException {
        assertEquals("29/10/2024 1234", new Event("test",
                "29/10/2024 1234", "29/10/2024 1235").getFrom());
    }

    @Test
    public void getTo_validInput_properDates() throws DestinyException {
        assertEquals("29/10/2024 1235", new Event("test",
                "29/10/2024 1234", "29/10/2024 1235").getTo());
    }

    @Test
    public void getFromAndTo_invalidInput_exceptionThrown() {
        try {
            assertEquals("32/13/2024 4567", new Event("test",
                    "32/13/2024 4567", "32/14/2024 4567").getFrom());
        } catch (DestinyException e) {
            assertEquals("Something went wrong...\n" + "The time format in \"from\" or \"to\" is wrong\n"
                    + "Please enter the date and time in the following format:\n"
                    + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234)", e.getMessage());
        }
    }
}
