package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.Ui;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {

    @Test
    public void execute_mark_success() {
        MarkCommand mc = new MarkCommand("mark 2", true);
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        Todo t = new Todo("read book");
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(t);
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            mc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            assertEquals("D | 1 | return book | 20-12-2020 10:01", list.getTask(1).toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_unmark_success() {
        MarkCommand mc = new MarkCommand("mark 2", false);
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        dl.done();
        Todo t = new Todo("read book");
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(t);
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            mc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            assertEquals("D | 0 | return book | 20-12-2020 10:01", list.getTask(1).toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_markNegative_DukeException() {
        MarkCommand mc = new MarkCommand("mark -2", true);
        Todo t = new Todo("read book");
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(t);
        TaskList list = new TaskList(arr);
        try {
            mc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
        } catch (DukeException e) {
            assertEquals("Task (-2) not found.\n\t Here are the tasks in your list:\n\t\t 1." + t.printTask(),
                    e.getMessage());
        }
    }
}
