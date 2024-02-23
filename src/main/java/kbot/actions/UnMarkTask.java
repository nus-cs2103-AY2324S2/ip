package kbot.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import kbot.managers.TaskFileManager;
import kbot.managers.TaskManager;
import kbot.tasks.Task;

/**
 * A unmark command that marks a Task as not completed.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class UnMarkTask extends Command {
    /** Index where we mark Task as not complete. */
    private int index;

    /**
     * Constructor for UnMarkTask.
     * 
     * @param index Index where we unmark the task.
     */
    public UnMarkTask(int index) {
        this.index = index;
    }

    /**
     * Marks a Task as not completed.
     * 
     * @return String of whether there has been an error or a success.
     * @throws IndexOutOfBoundsException Throws the exception when the index to mark
     *                                   is out of range.
     * @throws IOException               When saving to file and the directory does
     *                                   not exist.
     */
    public String execute() throws IndexOutOfBoundsException, IOException {
        try {
            assert TaskManager.getTasks().size() > 0 : "There are no tasks in the tasklist to unmark!";
            Task taskToUnmark = TaskManager.getTasks().get(index); // may throw IndexOutOfBoundsException
            taskToUnmark.setNotCompleted();
            TaskFileManager.saveTasksToFile(TaskManager.getTasks()); // may throw IOException
            return ("OK, I've marked this task as not done yet:\n" + taskToUnmark);
        } catch (IndexOutOfBoundsException e) {
            return (e.getMessage());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }
}
