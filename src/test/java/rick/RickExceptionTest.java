package rick;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RickExceptionTest {
    @Test
    public void messageTest() {
        String message = "Hello!";
        assertEquals(new RickException(message).getMessage(), message);
    }
}
