package echon;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void constructor_normalInput_parsedCorrectly() {
        assertDoesNotThrow(() -> new Deadline("this is a string", "2024-01-26 23:59"));
    }

    @Test
    public void constructor_wrongDateFormat_throwsException() {
        assertThrows(EchonException.class, () -> new Deadline("this is a string", "26/1/2024 23:59"));
    }

    @Test
    public void toString_normalInput_formattedCorrectly() {
        try {
            assertEquals("[D][ ] this is a string (by: Jan 26 2024 23:59)",
                    new Deadline("this is a string", "2024-01-26 23:59").toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void toFileLine_normalInput_formattedCorrectly() {
        try {
            assertEquals("D | 0 | this is a string | 2024-01-26 23:59",
                    new Deadline("this is a string", "2024-01-26 23:59").toFileLine());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void markAsDone_normalInput_markedCorrectly() {
        try {
            Deadline deadline = new Deadline("this is a string", "2024-01-26 23:59");
            deadline.markAsDone();
            assertEquals("[D][X] this is a string (by: Jan 26 2024 23:59)", deadline.toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void unmarkAsDone_normalInput_unmarkedCorrectly() {
        try {
            Deadline deadline = new Deadline("this is a string", "2024-01-26 23:59");
            deadline.markAsDone();
            deadline.unmarkAsDone();
            assertEquals("[D][ ] this is a string (by: Jan 26 2024 23:59)", deadline.toString());
        } catch (EchonException e) {
            fail();
        }
    }

    @Test
    public void getDescription_normalInput_returnCorrectly() {
        try {
            assertEquals("this is a string", new Deadline("this is a string", "2024-01-26 23:59").getDescription());
        } catch (EchonException e) {
            fail();
        }
    }
}
