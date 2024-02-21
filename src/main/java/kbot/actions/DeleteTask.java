package kbot.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import kbot.main.TaskFileManager;
import kbot.main.TaskManager;
import kbot.tasks.Task;

/**
 * A delete command that deletes a Task from the task list.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class DeleteTask extends Command {
    /** Index where Task is removed. */
    private int index;

    /**
     * Constructor for deleting the task.
     * 
     * @param index Index where Task is removed.
     */
    public DeleteTask(int index) {
        this.index = index;
    }

    /**
     * Deletes an existing task.
     * 
     * @return String of whether there has been an error or a success.
     * @throws IndexOutOfBoundsException If the index to mark is out of range.
     * @throws IOException               If saving to file and the directory does
     *                                   not exist.
     */
    public String execute() throws IndexOutOfBoundsException, IOException {
        try {
            assert TaskManager.getTasks().size() > 0 : "There are no tasks in the tasklist to delete!";
            Task taskToDelete = TaskManager.getTasks().remove(index); // may throw IndexOutOfBoundsException
            TaskFileManager.saveTasksToFile(TaskManager.getTasks()); // may throw IOException
            return ("OK, I've deleted this task:\n" + taskToDelete
                    + "\nNow you have " + TaskManager.getTasks().size() + " tasks in this list!");
        } catch (IndexOutOfBoundsException e) {
            return (e.getMessage());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }
}
