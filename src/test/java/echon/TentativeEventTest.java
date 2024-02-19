package echon;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TentativeEventTest {
    private TentativeEvent createEventWithMultipleSlots() throws EchonException {
        TentativeEvent event = new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59");
        event.addTentativeSlot("2024-01-28 23:59", "2024-01-29 23:59");
        event.addTentativeSlot("2024-01-30 23:59", "2024-01-31 23:59");
        return event;
    }

    @Test
    public void constructor_normalInput_parsedCorrectly() {
        assertDoesNotThrow(() -> new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59"));
    }

    @Test
    public void constructor_wrongDateFormat_throwsException() {
        assertThrows(EchonException.class, () -> new TentativeEvent("this is a string",
                "26/1/2024 23:59", "27/1/2024 23:59"));
    }

    @Test
    public void addTentativeSlot_normalInput_noExceptions() {
        try {
            TentativeEvent event = new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59");
            assertDoesNotThrow(() -> event.addTentativeSlot("2024-01-28 23:59", "2024-01-29 23:59"));
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void addTentativeSlot_wrongDateFormat_throwsException() {
        try {
            TentativeEvent event = new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59");
            assertThrows(EchonException.class, () -> event.addTentativeSlot("26/1/2024 23:59", "27/1/2024 23:59"));
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void addTentativeSlot_wrongDateFormat_stateUnchanged() {
        try {
            TentativeEvent event = new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59");
            assertThrows(EchonException.class, () -> event.addTentativeSlot("26/1/2024 23:59", "27/1/2024 23:59"));
            assertEquals("[E][ ] this is a string (tentative slot 1 from: Jan 26 2024 23:59 to: Jan 27 2024 23:59)",
                    event.toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void toString_normalInput_formattedCorrectly() {
        try {
            assertEquals("[E][ ] this is a string (tentative slot 1 from: Jan 26 2024 23:59 to: Jan 27 2024 23:59)",
                    new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59").toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void toString_multipleSlots_formattedCorrectly() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertEquals("[E][ ] this is a string (tentative slot 1 from: Jan 26 2024 23:59 to: Jan 27 2024 23:59)"
                    + " (tentative slot 2 from: Jan 28 2024 23:59 to: Jan 29 2024 23:59)"
                    + " (tentative slot 3 from: Jan 30 2024 23:59 to: Jan 31 2024 23:59)",
                    event.toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void toFileLine_normalInput_formattedCorrectly() {
        try {
            assertEquals("TE | 0 | this is a string | 2024-01-26 23:59 | 2024-01-27 23:59",
                    new TentativeEvent("this is a string", "2024-01-26 23:59", "2024-01-27 23:59").toFileLine());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void toFileLine_multipleSlots_formattedCorrectly() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertEquals("TE | 0 | this is a string | 2024-01-26 23:59 | 2024-01-27 23:59"
                    + " | 2024-01-28 23:59 | 2024-01-29 23:59 | 2024-01-30 23:59 | 2024-01-31 23:59",
                    event.toFileLine());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void createEvent_firstSlot_eventCreated() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertEquals("[E][ ] this is a string (from: Jan 26 2024 23:59 to: Jan 27 2024 23:59)",
                    event.createEvent(0).toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void createEvent_secondSlot_eventCreated() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertEquals("[E][ ] this is a string (from: Jan 28 2024 23:59 to: Jan 29 2024 23:59)",
                    event.createEvent(1).toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void createEvent_thirdSlot_eventCreated() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertEquals("[E][ ] this is a string (from: Jan 30 2024 23:59 to: Jan 31 2024 23:59)",
                    event.createEvent(2).toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void createEvent_outOfRangeLarge_exceptionThrown() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertThrows(EchonException.class, () -> event.createEvent(3));
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void createEvent_outOfRangeSmall_exceptionThrown() {
        try {
            TentativeEvent event = createEventWithMultipleSlots();
            assertThrows(EchonException.class, () -> event.createEvent(-1));
        } catch (EchonException e) {
            fail();
        }
    }
}
