package rick;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rick.logic.RickException;

public class RickExceptionTest {
    @Test
    public void messageTest() {
        String message = "Hello!";
        assertEquals(new RickException(message).getMessage(), message);
    }
}
