package duke.util;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;
import duke.exceptions.DukeException;
import duke.task.DeadlineTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @Test
    public void invalid_todo() {
        try {
            assertEquals(new TodoTask("", ""), Parser.parse_todo("todo"));
        } catch (Exception e) {
            assertEquals("\tTodo description cannot be empty!\n\tEx: todo return book\n", e.getMessage());
        }
    }

    @Test
    public void deadline_success() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            assertEquals(new DeadlineTask("eat", LocalDateTime.parse("7/2/2024 1800", formatter), "deadline eat /by 7/2/2024 1800").toString(), Parser.parse_deadline("deadline eat /by 7/2/2024 1800").toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
