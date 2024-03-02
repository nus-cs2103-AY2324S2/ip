package grizzly.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MissingParameterExceptionTest {
    @Test
    public void testMissingParameterException() {
        MissingParameterException e = new MissingParameterException("test");
        assertEquals(e.getMessage(), "Missing parameters: test");
    }
}
