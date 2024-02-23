package duke;

import command.AddTaskCommand;

import command.ExitCommand;

import command.SearchDateCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_AddTaskCommandCreated_successfully() throws DukeException {
        assertEquals(new AddTaskCommand("todo return book"),Parser.parse("todo return book"));
    }

    @Test
    public void parse_ExitCommandCreated_successfully() throws DukeException {
        assertEquals(new ExitCommand(),Parser.parse("bye"));
    }

    @Test
    public void parse_SearchDateCommandCreated_successfully() throws DukeException {
        assertEquals(new SearchDateCommand("2019-12-02"),Parser.parse("view 2-12-2019"));
    }
}
