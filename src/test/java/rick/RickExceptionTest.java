package rick;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RickExceptionTest {
    @Test
    public void messageTest () {
        String message = "Hello!";
        assertEquals(new RickException(message).getMessage(), message);
    }
}
