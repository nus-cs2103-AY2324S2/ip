package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.StorageStub;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {

    @Test
    public void execute_deleteFormat_Deleted() {
        DeleteCommand dc = new DeleteCommand("delete 1");
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            assertEquals(true, list.getList().isEmpty());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void execute_deleteNegativeIndex_DukeException() {
        DeleteCommand dc = new DeleteCommand("delete -1");
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(dl);
        TaskList list = new TaskList(arr);
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            fail();
        } catch (DukeException e) {
            assertEquals("Task (-1) not found.\n" + "\t Here are the tasks in your list:\n\t\t 1." + dl.printTask(), e.getMessage());
        }
    }

    @Test
    public void execute_deleteFromEmptyList_DukeException() {
        DeleteCommand dc = new DeleteCommand("delete 1");
        TaskList list = new TaskList();
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub("", ""));
            fail();
        } catch (DukeException e) {
            assertEquals("Task (1) not found.\n\t No task in list.", e.getMessage());
        }
    }
}
