package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.task.Deadline;
import duke.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void parse_todo_command() throws Exception {
        String input = "todo read book";
        Todo output = new Todo("read book", false, -1, "T");
        Command cmd = new AddCommand(output);
        assertEquals(cmd.getClass(), Parser.parse(input).getClass());
    }

    @Test
    void parse_deadline_command() throws Exception {
        String input = "deadline return book /by 2024/04/01 10:00";
        Deadline output = new Deadline("return book", false, -1, "D", "2024/04/01 10:00");
        Command cmd = new AddCommand(output);
        assertEquals(cmd.getClass(), Parser.parse(input).getClass());
    }

}