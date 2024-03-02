package grizzly.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MissingInformationExceptionTest {
    @Test
    public void testMissingInformationException() {
        MissingInformationException e = new MissingInformationException("test");
        assertEquals(e.getMessage(), "Missing information: test");
    }
}
