package duke.utils;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.GenerateTaskCommand;
import duke.commands.ModifyTaskCommand;
import duke.exceptions.NoSuchCommandException;

public class ParserTest {
    
    @Test
    public void GenerateTodoTest() {
        try {
            Command c = Parser.parse("todo task1");
            assert (c instanceof GenerateTaskCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void GenerateDeadlineTest() {
        try {
            Command c = Parser.parse("deadline task2 /by 12/11/2024, 13:00");
            assert (c instanceof GenerateTaskCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }
    
    @Test
    public void GenerateModificationTest() {
        try {
            Command c = Parser.parse("mark 2");
            assert (c instanceof ModifyTaskCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void GenerateModificationTest2() {
        try {
            Command c = Parser.parse("unmark 2");
            assert (c instanceof ModifyTaskCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void GenerateExitTest() {
        try {
            Command c = Parser.parse("bye");
            assert (c instanceof ExitCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void InvalidCommandTest() {
        try {
            Command c = Parser.parse("rjksjrkr");
            fail();
        } catch (NoSuchCommandException e) {
            return;
        }
    }


}
