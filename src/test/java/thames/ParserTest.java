package thames;

import thames.command.AddCommand;
import thames.command.Command;
import thames.task.Event;
import thames.task.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {
    @Test
    public void parse_UnknownInput_ExceptionThrown() {
        try {
            Command c = Parser.parse("hi");
            fail();
        } catch (ThamesException e) {
            assertEquals("Sorry, I do not understand what that means.", e.getMessage());
        }
    }

    @Test
    public void parse_WrongInput_ExceptionThrown() {
        try {
            Command c = Parser.parse("Event read book /from /to 2024-01-01");
            fail();
        } catch (ThamesException e) {
            assertEquals("Please provide your Event task in the following format: \n" +
                    "Event <task name> /by <yyyy-mm-dd>\n", e.getMessage());
        }

        try {
            Command c = Parser.parse("Event read book book /from /from 2024-01-01 /to 2024-01-01");
            fail();
        } catch (ThamesException e) {
            assertEquals("Please provide your Event task in the following format: \n" +
                    "Event <task name> /by <yyyy-mm-dd>\n", e.getMessage());
        }

        try {
            Command c = Parser.parse("Deadline read book /by");
            fail();
        } catch (ThamesException e) {
            assertEquals("Please provide your Deadline task in the following format: \n" +
                    "Deadline <task name> /by <yyyy-mm-dd>\n", e.getMessage());
        }
    }

    @Test
    public void parse_AddEvent() throws ThamesException{
        Command c1 = Parser.parse("Event read book /from 2024-01-01 /to 2024-01-02");
        assertEquals(new AddCommand(new Event("read book", "2024-01-01", "2024-01-02")), c1 );

        Command c2 = Parser.parse("Event cook/from2024-04-04/to2024-05-05");
        assertEquals(new AddCommand(new Event("cook", "2024-04-04", "2024-05-05")), c2 );
    }

    @Test
    public void parse_AddDeadline() throws ThamesException{
        Command c1 = Parser.parse("Deadline read book /by 2022-01-01");
        assertEquals(new AddCommand(new Deadline("read book", "2022-01-01")), c1 );
    }
}
