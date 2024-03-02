package grizzly.exceptions;

import org.junit.jupiter.api.Test;

public class RecordDeletionExceptionTest {
    @Test
    public void testRecordDeletionException() {
        RecordDeletionException e = new RecordDeletionException("test");
        assert e.getMessage().equals("Error Deleting Record! test");
    }
}
