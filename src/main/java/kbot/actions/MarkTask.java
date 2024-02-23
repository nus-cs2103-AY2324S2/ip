package kbot.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import kbot.managers.TaskFileManager;
import kbot.managers.TaskManager;
import kbot.tasks.Task;

/**
 * A mark command that marks a Task as completed.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class MarkTask extends Command {
    /** Index where Task is marked as completed. */
    private int index;

    /**
     * Constructor for MarkTask.
     * 
     * @param index Index where Task is marked.
     */
    public MarkTask(int index) {
        this.index = index;
    }

    /**
     * Marks a Task as completed.
     * 
     * @return String of whether there has been an error or a success.
     * @throws IndexOutOfBoundsException Throws the exception when the index to mark
     *                                   is out of range.
     * @throws IOException               When saving to file and the directory does
     *                                   not exist.
     */
    public String execute() throws IndexOutOfBoundsException, IOException {
        try {
            assert TaskManager.getTasks().size() > 0 : "There are no tasks in the tasklist to mark!";
            Task taskToMark = TaskManager.getTasks().get(index); // may throw IndexOutOfBoundsException
            taskToMark.setCompleted();
            TaskFileManager.saveTasksToFile(TaskManager.getTasks()); // may throw IOException
            return ("Nice! I've marked this task as done:\n" + taskToMark);
        } catch (IndexOutOfBoundsException e) {
            return (e.getMessage());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }
}
