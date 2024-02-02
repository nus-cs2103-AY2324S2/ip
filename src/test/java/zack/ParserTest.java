package zack;

import org.junit.jupiter.api.Test;
import zack.commands.AddTaskCommand;
import zack.commands.ByeCommand;
import zack.commands.Command;
import zack.commands.DateCommand;
import zack.commands.DeleteCommand;
import zack.commands.ListCommand;
import zack.commands.MarkCommand;
import zack.util.Parser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_byeCommand_byeCommandReturned() throws Exception {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void parse_markCommandWithNumericIndex_markCommandReturned() throws Exception {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkCommandWithNumericIndex_markCommandReturned() throws Exception {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkCommand); // Assuming MarkCommand is used for both marking and unmarking
    }

    @Test
    public void parse_listCommand_listCommandReturned() throws Exception {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_todoCommandWithDescription_addTaskCommandReturned() throws Exception {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddTaskCommand);
    }

    @Test
    public void parse_deadlineCommandWithDescriptionAndDate_addTaskCommandReturned() throws Exception {
        Command command = Parser.parse("deadline submit assignment /by 2022-12-25");
        assertTrue(command instanceof AddTaskCommand);
    }

    @Test
    public void parse_eventCommandWithDescriptionAndDate_addTaskCommandReturned() throws Exception {
        Command command = Parser.parse("event project meeting /at 2022-12-26");
        assertTrue(command instanceof AddTaskCommand);
    }

    @Test
    public void parse_deleteCommandWithNumericIndex_deleteCommandReturned() throws Exception {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_dateCommandWithValidDate_dateCommandReturned() throws Exception {
        Command command = Parser.parse("date 2022-12-25");
        assertTrue(command instanceof DateCommand);
    }

    @Test
    public void parse_invalidCommandInput_exceptionThrown() {
        Exception exception = assertThrows(ZackException.class, () -> Parser.parse("invalid"));
        String expectedMessage = "I'm sorry, but I don't know what that means :-(";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void parse_markCommandWithInvalidIndex_exceptionThrown() {
        Exception exception = assertThrows(ZackException.class, () -> Parser.parse("mark xyz"));
        String expectedMessage = "Invalid task index. Please enter a valid number.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void parse_dateCommandWithInvalidDateFormat_exceptionThrown() {
        Exception exception = assertThrows(ZackException.class, () -> Parser.parse("date 2022/12/25"));
        String expectedMessage = "Invalid date format. Please enter a date in yyyy-MM-dd format.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
