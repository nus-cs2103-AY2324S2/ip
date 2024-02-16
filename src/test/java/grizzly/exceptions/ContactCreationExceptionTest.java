package grizzly.exceptions;

import org.junit.jupiter.api.Test;

public class ContactCreationExceptionTest {
    @Test
    public void testContactCreationException() {
        ContactCreationException e = new ContactCreationException("test");
        assert e.getMessage().equals("Error Creating Contact! test");
    }
}
