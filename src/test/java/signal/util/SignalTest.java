package signal.util;

import org.junit.jupiter.api.Test;
import signal.SignalException;
import signal.task.Deadline;
import signal.task.Task;
import signal.task.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test methods taskCommands in Parser and commandFind in Ui
 */
public class SignalTest {
    private Ui ui = new Ui();
    private Parser parse = new Parser();

    @Test
    public void testTasks1(){
        // todo normal usage
        String expected = "Got it! I've added this task to your list:\n" +
                "  T | 0 | read book \n" +
                "Now you have 1 task in the list.\n";
        String test = parse.taskCommands("todo read book");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks2() {
        // todo with empty task
        String expected = "Looks like you haven't entered a task description!";
        String test = parse.taskCommands("todo");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks3() {
        // deadline normal usage
        String expected = "Got it! I've added this task to your list:\n" +
                "  D | 0 | homework | by: 23 Feb 2024 11:59 pm\n" +
                "Now you have 1 task in the list.\n";
        String test = parse.taskCommands("deadline homework /by 2024-02-23 23:59:59");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks4() {
        // deadline with empty task
        String expected = "Looks like you haven't entered a task description!";
        String test = parse.taskCommands("deadline");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks5() {
        // deadline with empty deadline
        String expected = "Looks like you haven't added a deadline!";
        String test = parse.taskCommands("deadline homework");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks6() {
        // deadline with wrong date/time format
        String expected = "Oops, I can't read the date or time like that! Please use yyyy-mm-dd for date and hh:mm:ss for time.";
        String test = parse.taskCommands("deadline homework /by date");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks7() {
        // deadline with empty description
        String expected = "Looks like you haven't entered a task description!";
        String test = parse.taskCommands("deadline /by 2024-02-23");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks8() {
        // event normal usage
        String expected = "Got it! I've added this task to your list:\n" +
                "  E | 0 | birthday | from: 12 Dec 2024| to: 12 Dec 2024\n" +
                "Now you have 1 task in the list.\n";
        String test = parse.taskCommands("event birthday /from 2024-12-12 /to 2024-12-12");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks9() {
        // deadline with empty task
        String expected = "Looks like you haven't entered a task description!";
        String test = parse.taskCommands("event");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks10() {
        // deadline with no to
        String expected = "Please tell me when the event ends.";
        String test = parse.taskCommands("event birthday /from 2024-12-12");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks11() {
        // deadline with wrong date/time format
        String expected = "Oops, I can't read the date or time like that! Please use yyyy-mm-dd for date and hh:mm:ss for time.";
        String test = parse.taskCommands("event birthday /from 12 dec /to 12 dec");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks12() {
        // deadline with empty description
        String expected = "Looks like you haven't entered a task description!";
        String test = parse.taskCommands("event /from 2024-02-23 /to 2024-02-23");
        assertEquals(expected, test);
    }

    @Test
    public void testTasks13() {
        // event with no end date
        String expected = "Please tell me when the event ends.";
        String test = parse.taskCommands("event birthday /from 2024-02-23");
        assertEquals(expected, test);
    }

    @Test
    public void testFind1() {
        // find with empty search
        String expected = "I don't know what you're looking for!";
        try {
            String[] input = {"find"};
            ui.commandFind(input);
        } catch (SignalException e) {
            assertEquals(expected, e.getMessage());
        }

    }

    @Test
    public void testFind2() throws SignalException {
        // find with empty tasklist
        String expected = "Looks like there's nothing here. Try another keyword!\n";
        String[] input = {"find", "this"};
        String test = ui.commandFind(input);
        assertEquals(expected, test);
    }

    @Test
    public void testFind3() throws SignalException {
        // find working correctly
        String expected = "Sure, here are the tasks containing 'read':\n" +
                "1. T | 0 | read book \n" +
                "2. D | 0 | read article | by: 25 Feb 2024\n";
        String[] input = {"find", "read"};
        ArrayList<Task> tL = new ArrayList<Task>();
        tL.add(new ToDo("read book"));
        tL.add(new Deadline("read article", "2024-02-25"));
        Ui ui2 = new Ui(tL, new Storage());
        String test = ui2.commandFind(input);
        assertEquals(expected, test);
    }
}
