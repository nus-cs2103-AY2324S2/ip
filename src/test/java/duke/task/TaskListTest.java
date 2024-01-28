package duke.task;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void markTaskTest(){
        try {
            TaskList tskLst = new TaskList(new ArrayList<Task>());
            Storage storage = new Storage("src/main/data/test/caching.txt");
            tskLst.addTask(TaskList.TaskCommand.DEADLINE, "deadline return book /by Sunday", storage);
            tskLst.mark("mark 1", storage);
            assertEquals(true, tskLst.instrList.get(0).isDone);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void unmarkTaskTest(){
        try {
            TaskList tskLst = new TaskList(new ArrayList<Task>());
            Storage storage = new Storage("src/main/data/test/caching.txt");
            tskLst.addTask(TaskList.TaskCommand.DEADLINE, "deadline return book /by Sunday", storage);
            tskLst.unmark("unmark 1", storage);
            assertEquals(false, tskLst.instrList.get(0).isDone);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}
