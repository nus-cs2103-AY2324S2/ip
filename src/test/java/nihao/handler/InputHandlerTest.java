package nihao.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import nihao.action.DeleteAction;
import nihao.action.ExitAction;
import nihao.action.ListAction;
import nihao.action.MarkAction;
import nihao.action.TaskAction;
import nihao.action.UnmarkAction;
import nihao.action.task.DeadlineTask;
import nihao.action.task.EventTask;
import nihao.action.task.TodoTask;

class InputHandlerTest {
    @Test
    void handleInput_success() throws Exception {
        // "bye" command
        assertEquals(new ExitAction(), InputHandler.handleInput("bye"));

        // "list" command
        assertEquals(new ListAction(), InputHandler.handleInput("list"));

        // "mark" command
        assertEquals(new MarkAction("2"), InputHandler.handleInput("mark 2"));

        // "unmark" command
        assertEquals(new UnmarkAction("2"), InputHandler.handleInput("unmark 2"));

        // "delete" command
        assertEquals(new DeleteAction("3"), InputHandler.handleInput("delete 3"));

        // "todo" command
        assertEquals(new TaskAction(new TodoTask("morning")), InputHandler.handleInput("todo morning"));

        String dateTime = "10/02/2024 2000";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

        // "deadline" command
        assertEquals(new TaskAction(
                new DeadlineTask("homework", localDateTime)),
                InputHandler.handleInput("deadline homework /by " + dateTime));

        // "event" command
        assertEquals(new TaskAction(
                new EventTask("homework", localDateTime, localDateTime)),
                InputHandler.handleInput("event homework /from " + dateTime + " /to " + dateTime));
    }

    @Test
    void handleInput_exceptionThrown() {
        try {
            InputHandler.handleInput("waibi babu");
            fail();
        } catch (Exception e) {
            assertEquals("UnknownCommandException: 'waibi babu' is unknown.", e.getMessage());
        }

        try {
            InputHandler.handleInput("mark 3 4");
            fail();
        } catch (Exception e) {
            assertEquals("IllegalArgumentException: mark expects 1 arguments.", e.getMessage());
        }

        try {
            InputHandler.handleInput("mark ");
            fail();
        } catch (Exception e) {
            assertEquals("IllegalArgumentException: mark expects 1 arguments.", e.getMessage());
        }

        try {
            InputHandler.handleInput("todo");
            fail();
        } catch (Exception e) {
            assertEquals("IllegalArgumentException: todo requires at least 1 argument.", e.getMessage());
        }

        try {
            InputHandler.handleInput("deadline /by /by");
            fail();
        } catch (Exception e) {
            assertEquals("IllegalArgumentException: deadline requires exactly 1 /by flag.", e.getMessage());
        }

        try {
            InputHandler.handleInput("deadline ddl /by");
            fail();
        } catch (Exception e) {
            assertEquals("IllegalArgumentException: illegal use of /by flag.", e.getMessage());
        }

        try {
            InputHandler.handleInput("event /from /to 09/02/2024 2000");
            fail();
        } catch (Exception e) {
            assertEquals("IllegalArgumentException: illegal use of flags.", e.getMessage());
        }
    }
}
