package duke.task;

import duke.task.TodoTask;
import duke.util.Parser;
import org.junit.jupiter.api.Test;
import duke.exceptions.DukeException;
import duke.task.DeadlineTask;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void invalid_constructor() {
        try {
            assertEquals(new TodoTask("", ""), Parser.parse_todo("todo"));
        } catch (Exception e) {
            assertEquals("\tTodo description cannot be empty!\n\tEx: todo return book\n", e.getMessage());
        }
    }

    @Test
    public void success_instance() {
        try {
            assertEquals(new TodoTask("read", "todo read").toString(), Parser.parse_todo("todo read").toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
