package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void markTest() {
        Todo T = new Todo("allo", false);
        T.mark();
        System.out.println(T.toString());
        assertEquals(T.toString(), "[T][X]allo");
    }
}
