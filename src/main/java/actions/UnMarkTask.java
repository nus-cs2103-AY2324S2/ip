package actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import KBot.TaskFileManager;
import KBot.TaskManager;
import tasks.Task;

public class UnMarkTask extends Command {

    private int index;

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
            Task t = TaskManager.getTasks().get(index); // may throw IndexOutOfBoundsException
            t.setNotCompleted();
            TaskFileManager.saveTasksToFile(TaskManager.getTasks()); // may throw IOException
            return ("OK, I've marked this task as not done yet:\n" + t);
        } catch (IndexOutOfBoundsException e) {
            return (e.getMessage());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }

    public String toString() {
        return "Command: UnMarkTask at index " + index;
    }
}
