package meanduke.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import meanduke.commands.AddCommand;
import meanduke.exceptions.InvalidCommandException;
import meanduke.tasks.TaskList;


public class ParserTest {
    @Test
    public void parseAddWithoutType() {
        try {
            TaskList taskList = new TaskList(); //No need for stub as method only passes this instance
            Parser.parseAdd("WrongType blah blah", taskList);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Usage: add <type> ...", e.getMessage());
        }
    }

    @Test
    public void parseAddWithoutDesc() {
        try {
            TaskList taskList = new TaskList(); //No need for stub as method only passes this instance
            Parser.parseAdd("event /from 2023-12-12 /to 2022-12-31", taskList);
            fail();
        } catch (Exception e) {
            assertEquals("Usage: add event <description> /from <YYYY-MM-DD> [HH:MM] /to <YYYY-MM-DD> [HH:MM]",
                    e.getMessage());
        }
    }

    @Test
    public void parseAddWithoutDate() {
        try {
            TaskList taskList = new TaskList(); //No need for stub as method only passes this instance
            Parser.parseAdd("event testDesc /from 23:23 /to 2022-12-31", taskList);
            fail();
        } catch (Exception e) {
            assertEquals("Usage: add event <description> /from <YYYY-MM-DD> [HH:MM] /to <YYYY-MM-DD> [HH:MM]",
                    e.getMessage());
        }
    }

    @Test
    public void parseAddRandomSpaces() {
        try {
            TaskList taskList = new TaskList(); //No need for stub as method only passes this instance
            AddCommand cmd = Parser.parseAdd(
                    "event   testDesc  /from   2023-06-07   23:23  /to   2022-12-31  ", taskList);
            cmd.execute();
            assertEquals("1. [E][ ] testDesc (7 JUNE 2023 23:23 - 31 DECEMBER 2022)\n", taskList.toString());
        } catch (Exception e) {
            fail();
        }

    }


}
