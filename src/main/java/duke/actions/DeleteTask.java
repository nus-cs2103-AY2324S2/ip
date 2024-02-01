package duke.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import duke.kbot.TaskFileManager;
import duke.kbot.TaskManager;
import duke.tasks.Task;

public class DeleteTask extends Command {
    private int index;

    public DeleteTask(int index) {
        this.index = index;
    }

    /**
     * Deletes an existing task.
     * 
     * @return String of whether there has been an error or a success.
     * @throws IndexOutOfBoundsException Throws the exception when the index to mark
     *                                   is out of range.
     * @throws IOException               When saving to file and the directory does
     *                                   not exist.
     */
    public String execute() throws IndexOutOfBoundsException, IOException {
        try {
            Task t = TaskManager.getTasks().remove(index); // may throw IndexOutOfBoundsException
            TaskFileManager.saveTasksToFile(TaskManager.getTasks()); // may throw IOException
            return ("OK, I've deleted this task:\n" + t
                    + "\nNow you have " + TaskManager.getTasks().size() + " tasks in this list!");
        } catch (IndexOutOfBoundsException e) {
            return (e.getMessage());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }

    public String toString() {
        return "Command: Delete at index " + index;
    }

}
