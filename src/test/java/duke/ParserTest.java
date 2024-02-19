package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    @DisplayName("Parse todo command correctly")
    void testParseCommand() {
        String input = "todo read book";
        String deadlineInput = "deadline read book /by 2024-02-20";
        String eventInput = "event read book /from 2024-02-20 /to 2024-03-20";
        Parser.ParsedCommand parsedCommand = Parser.parse(input);
        Parser.ParsedCommand parsedCommand2 = Parser.parse(deadlineInput);
        Parser.ParsedCommand parsedCommand3 = Parser.parse(eventInput);
        assertEquals(CommandType.TODO, parsedCommand.getCommandType(), "Command type should be TODO");
        assertEquals(CommandType.DEADLINE, parsedCommand2.getCommandType(), "Command type should be DEADLINE");
        assertEquals(CommandType.EVENT, parsedCommand3.getCommandType(), "Command type should be EVENT");
        assertEquals("todo read book", parsedCommand.getInput(), "Command details should match input");
        assertEquals("deadline read book /by 2024-02-20", parsedCommand2.getInput(), "Command details should match input");
        assertEquals("event read book /from 2024-02-20 /to 2024-03-20", parsedCommand3.getInput(), "Command details should match input");
    }

    @Test
    @DisplayName("Command created correctly")
    void testCreateTask() {
        String input = "todo read book";
        String deadlineInput = "deadline read book /by 2024-02-20";
        String eventInput = "event read book /from 2024-02-20 /to 2024-03-20";
        Parser.ParsedCommand parsedCommand = Parser.parse(input);
        Parser.ParsedCommand parsedCommand2 = Parser.parse(deadlineInput);
        Parser.ParsedCommand parsedCommand3 = Parser.parse(eventInput);
        Task createdTodo = Parser.createTask(parsedCommand.getCommandType(), parsedCommand.getInput());
        Task createdDeadline = Parser.createTask(parsedCommand2.getCommandType(), deadlineInput);
        Task createdEvent = Parser.createTask(parsedCommand3.getCommandType(), eventInput);
        assertEquals("[T][ ] read book", createdTodo.toString(), "Todo string output should match input");
        assertEquals("[D][ ] read book (by: Feb 20 2024)", createdDeadline.toString(), "Todo string output should match input");
        assertEquals("[E][ ] read book (from: Feb 20 2024 to: Mar 20 2024)", createdEvent.toString(), "Event string output should match input");
    }
    
    @Test
    @DisplayName("Handle invalid command")
    void testInvalidCommand() {
        String input = "invalid command";
        String input2 = "dead read book /by 24-02-20";
        String input3 = "    ";
        Parser.ParsedCommand parsedCommand = Parser.parse(input);
        Parser.ParsedCommand parsedCommand2 = Parser.parse(input2);
        Parser.ParsedCommand parsedCommand3 = Parser.parse(input3);
        assertEquals(CommandType.INVALID, parsedCommand.getCommandType(), "Invalid command should return INVALID type");
        assertEquals(CommandType.INVALID, parsedCommand2.getCommandType(), "Invalid command should return INVALID type");
        assertEquals(CommandType.INVALID, parsedCommand3.getCommandType(), "Invalid command should return INVALID type");
    }
}
