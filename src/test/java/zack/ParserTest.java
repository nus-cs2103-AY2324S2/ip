package zack;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import zack.commands.AddTaskCommand;
import zack.commands.ByeCommand;
import zack.commands.Command;
import zack.commands.DateCommand;
import zack.commands.DeleteCommand;
import zack.commands.FindCommand;
import zack.commands.ListCommand;
import zack.commands.MarkCommand;
import zack.util.Parser;


public class ParserTest {

    @Test
    public void parse_bye_byeCommandReturned() throws Exception {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void parse_markWithNumber_markCommandReturned() throws Exception {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkWithNumber_markCommandReturned() throws Exception {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkCommand); // Assuming MarkCommand is used for both marking and unmarking
    }

    @Test
    public void parse_list_listCommandReturned() throws Exception {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_todoWithDescription_addTaskCommandReturned() throws Exception {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddTaskCommand);
    }

    @Test
    public void parse_deadlineWithTime_addTaskCommandReturned() throws Exception {
        Command command = Parser.parse("deadline submit assignment /by 2022-12-25");
        assertTrue(command instanceof AddTaskCommand);
    }

    @Test
    public void parse_eventWithTime_addTaskCommandReturned() throws Exception {
        Command command = Parser.parse("event project meeting /from 2024-08-01 2200 "
                + "/to 2024-08-01 2200");
        assertTrue(command instanceof AddTaskCommand);
    }

    @Test
    public void parse_delete_deleteCommandReturned() throws Exception {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_dateWithValidDate_dateCommandReturned() throws Exception {
        Command command = Parser.parse("date 2022-12-25");
        assertTrue(command instanceof DateCommand);
    }

    @Test
    public void parse_findWithValidKeyword_findCommandReturned() throws Exception {
        Command command = Parser.parse("find home");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_invalidCommandInput_exceptionThrown() {
        Exception exception = assertThrows(ZackException.class, () -> Parser.parse("invalid"));
        String expectedMessage = "I'm sorry, but I don't know what that means :-(";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void parse_markWithInvalidIndex_exceptionThrown() {
        Exception exception = assertThrows(ZackException.class, () -> Parser.parse("mark xyz"));
        String expectedMessage = "You gotta gimme a proper number for the task index!";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void parse_dateWithInvalidDateFormat_exceptionThrown() {
        Exception exception = assertThrows(ZackException.class, () -> Parser.parse("date 2022/12/25"));
        String expectedMessage = "Sorry, but your date format has to be exactly like this: yyyy-MM-dd. "
                + "Take it up with the big man, I don't set the rules!";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
