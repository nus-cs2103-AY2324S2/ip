package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void showTasks_emptyTaskList_exceptionThrown() {
        try {
            new TaskList().showTasks();
            fail();
        } catch (DukeException e) {
            assertEquals("Add tasks to list first! Type something other than List/list or Bye/bye.\n", e.getMessage());
        }
    }

    @Test
    public void showTasks_filledTaskList_success() {
        ArrayList<Task> tasksToTest = new ArrayList<>();
        tasksToTest.add(new Todo(" read book"));
        tasksToTest.add(new Deadline(" read book", " 2020-10-10 18:00"));
        tasksToTest.add(new Event(" read book", " 2020-10-10 19:00 ", " 2020-10-11 19:00"));

        String expectedOutput = "Here are the tasks in your list:\n"
                + "\n"
                + "1.[T][ ] read book\n"
                + "2.[D][ ] read book (by:Oct 10 2020 18:00)\n"
                + "3.[E][ ] read book(from:Oct 10 2020 19:00 to:Oct 11 2020 19:00)\n\n";

        try {
            assertEquals(expectedOutput, new TaskList(tasksToTest).showTasks());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteMechanism_validInt_success() {
        ArrayList<Task> tasksToTest = new ArrayList<>();
        tasksToTest.add(new Todo(" read book"));
        tasksToTest.add(new Deadline(" read book", " 2020-10-10 18:00"));
        tasksToTest.add(new Event(" read book", " 2020-10-10 19:00 ", " 2020-10-11 19:00"));
    }

    @Test
    public void deleteMechanism_invalidInt_exceptionThrown() {
        ArrayList<Task> tasksToTest = new ArrayList<>();
        tasksToTest.add(new Todo(" read book"));
        tasksToTest.add(new Deadline(" read book", " 2020-10-10 18:00"));
        tasksToTest.add(new Event(" read book", " 2020-10-10 19:00 ", " 2020-10-11 19:00"));
        TaskList testing = new TaskList(tasksToTest);

        System.out.println("There should be an IndexOutOfBoundsException");
        org.junit.jupiter.api.Assertions
                .assertThrows(IndexOutOfBoundsException.class, () -> testing.deleteMechanism(9));

    }
}
