package command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import roland.Storage;
import roland.TaskList;
import roland.Ui;
import task.Task;

/**
 * The DeleteCommand class represents a command to delete a task from the TaskList based on its index.
 * It extends the Command class and implements the execute method to perform the deletion of the specified task.
 * Upon execution, it removes the task from the TaskList, updates the user interface, and provides feedback
 * about the successful removal, including the total number of tasks in the list.
 *
 * @author wolffe88
 */

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the taskList based on index
     * @param tasks The TaskList that stores the task
     * @param ui The user interface that outputs to the terminal
     * @param storage The storage path to store persistent data
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        serializeArrayList(tasks.getList(), storage.getFilePath());
        return ("I have removed " + task.toString()
                + " from your list. You have " + tasks.size() + " task(s) in list");
    }

    /**
     * Serializes the provided ArrayList of tasks to the specified file path using object serialization.
     *
     * @param list     The ArrayList of tasks to be serialized.
     * @param filePath The file path where the serialized data will be stored.
     */
    private static void serializeArrayList(ArrayList<Task> list, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
