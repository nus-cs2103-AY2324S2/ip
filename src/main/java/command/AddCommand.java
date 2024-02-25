package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;
import task.Task;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The AddCommand class represents a command to add a task to the TaskList.
 * It extends the Command class and implements the execute method to perform the addition of the task.
 * Upon execution, it adds the provided task to the TaskList, updates the user interface, and provides
 * feedback about the addition, including the total number of tasks in the list.
 *
 * @author wolffe88
 */

public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the specified task to the TaskList, and provides feedback
     * about the successful addition, including the total number of tasks in the list.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        serializeArrayList(tasks.getList(), storage.getFilePath());
        System.out.println(ui.getBot() + "I have added " + task.toString()
                + " to your list of tasks. You have " + tasks.size() + " task(s) in list");
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
