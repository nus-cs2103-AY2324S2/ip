package campus.exceptions;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class CampusExceptionTest {
    @Test
    public void testException() {
        try {
            throw new CampusException();
        } catch (CampusException e) {
            assert(true);
        }
    }

    @Test
    public void testExceptionMessage() {
        try {
            throw new CampusException("Test Message");
        } catch (CampusException e) {
            assert(Objects.equals(e.getMessage(), "Test Message"));
        }
    }


}
