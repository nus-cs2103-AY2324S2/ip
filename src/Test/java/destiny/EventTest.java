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
    public void getString_validInput_properString() throws DestinyException {
        assertEquals("[E][ ] test (from: Dec 12 2024 12:34PM  to: Dec 13 2024 12:34PM)",
                new Event("test", "12/12/2024 1234", "13/12/2024 1234").toString());
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
        try {
            assertEquals("11/11/2024 1234", new Event("test",
                    "12/12/2024 1234", "11/11/2024 1234").getTo());
        } catch (DestinyException e) {
            assertEquals("Something went wrong...\n"
                    + "The time in \"from\" comes after the time in \"to\"\n"
                    + "Please ensure that your \"from\" time is earlier than your \"to\" time", e.getMessage());
        }
    }
}
