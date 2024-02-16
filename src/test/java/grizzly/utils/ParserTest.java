package grizzly.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import grizzly.commands.Command;
import grizzly.commands.ExitCommand;
import grizzly.commands.GenerateRecordCommand;
import grizzly.commands.ModifyTaskCommand;
import grizzly.exceptions.NoSuchCommandException;

public class ParserTest {

    private Storage s;

    public ParserTest() {
        try {
            s = new Storage("data/test.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void generateTodoTest() {
        try {
            Database db = new Database();
            Command c = Parser.parse("todo task1");
            assertTrue((c instanceof GenerateRecordCommand));
            String output;
            try {
                output = c.execute(db, s);
            } catch (Exception e) {
                fail();
                return;
            }
            assertEquals(output, ("Todo Task added!\n[T][ ] task1\nYou now have 1 tasks in the list."));
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateDeadlineTest() {
        try {
            Database db = new Database();
            Command c = Parser.parse("deadline task2 /by 12/11/2024, 13:00");
            assertTrue((c instanceof GenerateRecordCommand));
            String output;
            try {
                output = c.execute(db, s);
            } catch (Exception e) {
                fail();
                return;
            }
            assertEquals(output, ("Deadline Task added!\n"
                                     + "[D][ ] task2\n"
                                     + "(by: 12 November 2024, 01:00PM)\n"
                                     + "You now have 1 tasks in the list."));
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateEventTest() {
        Database db = new Database();
        try {
            Command c = Parser.parse("event task3 /from 12/11/2024, 13:00 /to 12/11/2024, 14:00");
            assertTrue((c instanceof GenerateRecordCommand));
            String output;
            try {
                output = c.execute(db, s);
            } catch (Exception e) {
                fail();
                return;
            }
            assertEquals(output, ("Event Task added!\n"
                                     + "[E][ ] task3\n"
                                     + "(from: 12 November 2024, 01:00PM)\n"
                                     + "(to: 12 November 2024, 02:00PM)\n"
                                     + "You now have 1 tasks in the list."));
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateModificationTest() {
        try {
            Command c = Parser.parse("mark 2");
            assertTrue((c instanceof ModifyTaskCommand));
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateModificationTest2() {
        try {
            Command c = Parser.parse("unmark 2");
            assertTrue((c instanceof ModifyTaskCommand));
        } catch (NoSuchCommandException e) {
            fail();
        }
    }

    @Test
    public void generateExitTest() {
        try {
            Command c = Parser.parse("bye");
            assertTrue((c instanceof ExitCommand));
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
