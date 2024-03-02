package grizzly.exceptions;

import org.junit.jupiter.api.Test;

public class TaskModificationExceptionTest {
    @Test
    public void testTaskModificationException() {
        TaskModificationException e = new TaskModificationException("test");
        assert e.getMessage().equals("Error Modifying Task! test");
    }
}
