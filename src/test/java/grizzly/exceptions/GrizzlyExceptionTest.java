package grizzly.exceptions;

import org.junit.jupiter.api.Test;

public class GrizzlyExceptionTest {
    @Test
    public void testGrizzlyException() {
        GrizzlyException e = new GrizzlyException("test");
        assert e.getMessage().equals("test");
    }
}
