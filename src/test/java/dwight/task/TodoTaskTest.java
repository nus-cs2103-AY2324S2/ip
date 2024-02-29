package dwight.task;

import dwight.util.Parser;
import org.junit.jupiter.api.Test;
import dwight.exceptions.DwightException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void invalid_constructor() {
        try {
            assertEquals(new TodoTask("", ""), Parser.parseTodo("todo"));
        } catch (Exception e) {
            assertEquals("\tTodo description cannot be empty!\n\tEx: todo return book\n", e.getMessage());
        }
    }

    @Test
    public void success_instance() {
        try {
            assertEquals(new TodoTask("read", "todo read").toString(), Parser.parseTodo("todo read").toString());
        } catch (DwightException e) {
            System.out.println(e.getMessage());
        }
    }
}
