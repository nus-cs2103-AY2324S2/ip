package command;

import roland.Storage;
import task.Task;
import roland.TaskList;
import roland.Ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ExitCommand extends Command {

    /**
     * Executes the command by serializing the TaskList to the specified file path,
     * and prints a farewell message to the user interface.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        serializeArrayList(tasks.getList(), storage.getFilePath());
        System.out.println(ui.getBot() + "Bye. Hope to see you again soon!");

    }

    /**
     * Returns true to indicate that the program should exit when this command is executed.
     *
     * @return true, as the ExitCommand signifies the program's exit.
     */
    public boolean isExit() {
        return true;
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
