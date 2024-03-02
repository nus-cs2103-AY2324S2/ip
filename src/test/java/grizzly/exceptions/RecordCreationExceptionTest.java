package grizzly.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecordCreationExceptionTest {
    @Test
    public void testRecordCreationException() {
        RecordCreationException e = new RecordCreationException("test");
        assertEquals(e.getMessage(), "Error Creating Record! test");
    }

}
