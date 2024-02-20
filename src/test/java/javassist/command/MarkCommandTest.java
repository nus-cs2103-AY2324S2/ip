package javassist.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javassist.exception.JavAssistException;
import javassist.task.Deadline;
import javassist.task.Task;
import javassist.task.Todo;
import javassist.util.StorageStub;
import javassist.util.TaskList;
import javassist.util.Ui;



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
            mc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            assertEquals("D | 1 | return book | 20-12-2020 10:01", list.getTask(1).toString());
        } catch (JavAssistException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_unmark_success() {
        MarkCommand mc = new MarkCommand("mark 2", false);
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        dl.markAsNotDone();
        Todo t = new Todo("read book");
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(t);
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            mc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            assertEquals("D | 0 | return book | 20-12-2020 10:01", list.getTask(1).toString());
        } catch (JavAssistException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_markNegative_dukeException() {
        MarkCommand mc = new MarkCommand("mark -2", true);
        Todo t = new Todo("read book");
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(t);
        TaskList list = new TaskList(arr);
        try {
            mc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
        } catch (JavAssistException e) {
            assertEquals("Task (-2) not found.\nHere are the tasks in your list:\n1." + t.printTask(),
                    e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }
}
