package theadvisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void test1() throws TheAdvisorException {
        String input = "sleep";
        ToDos toDos = new ToDos(input);

        assertEquals("sleep", toDos.description);

        String input2 = "return my book";
        ToDos toDos2 = new ToDos(input2);

        assertEquals("return my book", toDos2.description);
    }

    @Test
    public void test2() throws TheAdvisorException {
        String input1 = "";
        assertThrows(AssertionError.class, () -> new ToDos(input1));
    }
}
