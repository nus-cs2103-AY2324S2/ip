package grizzly.utils;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import grizzly.commands.Command;
import grizzly.commands.ExitCommand;
import grizzly.commands.GenerateRecordCommand;
import grizzly.commands.ModifyTaskCommand;
import grizzly.exceptions.NoSuchCommandException;

public class ParserTest {

    @Test
    public void generateTodo() {
        try {
            Command c = Parser.parse("todo task1");
            assert (c instanceof GenerateRecordCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateDeadlineTest() {
        try {
            Command c = Parser.parse("deadline task2 /by 12/11/2024, 13:00");
            assert (c instanceof GenerateRecordCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateModificationTest() {
        try {
            Command c = Parser.parse("mark 2");
            assert (c instanceof ModifyTaskCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateModificationTest2() {
        try {
            Command c = Parser.parse("unmark 2");
            assert (c instanceof ModifyTaskCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateExitTest() {
        try {
            Command c = Parser.parse("bye");
            assert (c instanceof ExitCommand);
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void invalidCommandTest() {
        try {
            Command c = Parser.parse("rjksjrkr");
            fail();
        } catch (NoSuchCommandException e) {
            return;
        }
    }


}
