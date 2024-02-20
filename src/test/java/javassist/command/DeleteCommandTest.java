package javassist.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javassist.exception.JavAssistException;
import javassist.task.Deadline;
import javassist.task.Task;
import javassist.util.StorageStub;
import javassist.util.TaskList;
import javassist.util.Ui;





public class DeleteCommandTest {

    @Test
    public void execute_deleteFormat_deleted() {
        DeleteCommand dc = new DeleteCommand("delete 1");
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            assertEquals(true, list.getList().isEmpty());
        } catch (JavAssistException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_deleteNegativeIndex_dukeException() {
        DeleteCommand dc = new DeleteCommand("delete -1");
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            fail();
        } catch (JavAssistException e) {
            assertEquals("Task (-1) not found.\n" + "Here are the tasks in your list:\n1."
                    + dl.printTask(), e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_deleteFromEmptyList_dukeException() {
        DeleteCommand dc = new DeleteCommand("delete 1");
        TaskList list = new TaskList();
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            fail();
        } catch (JavAssistException e) {
            assertEquals("Task (1) not found.\nNo task in list.", e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }
}
