package actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import KBot.TaskFileManager;
import KBot.TaskManager;
import tasks.Task;

public class MarkTask extends Command {
    private int index;

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
            Task t = TaskManager.getTasks().get(index); // may throw IndexOutOfBoundsException
            t.setCompleted();
            TaskFileManager.saveTasksToFile(TaskManager.getTasks()); // may throw IOException
            return ("Nice! I've marked this task as done:\n" + t);
        } catch (IndexOutOfBoundsException e) {
            return (e.getMessage());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }

    public String toString() {
        return "Command: MarkTask at index " + index;
    }
}
