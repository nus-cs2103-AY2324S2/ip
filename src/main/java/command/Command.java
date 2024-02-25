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
 * The abstract Command class serves as a blueprint for various commands that can be executed
 * in the task management application. It provides an execute method to be implemented by its
 * concrete subclasses for carrying out specific actions, and a default isExit method to check
 * if the program should quit.
 *
 * Subclasses of Command are expected to provide concrete implementations for the execute method,
 * defining the behavior associated with the specific command.
 *
 * @author wolffe88
 */

public abstract class Command {



    /**
     * @param tasks The taskList that stores the task
     * @param ui The user interface that outputs to the terminal
     * @param storage The storage path to store persistent data
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the program should exit.
     *
     * @return A boolean value indicating whether the program should exit (true) or continue running (false).
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Serializes the provided ArrayList of tasks to the specified file path using object serialization.
     *
     * @param list     The ArrayList of tasks to be serialized.
     * @param filePath The file path where the serialized data will be stored.
     */
    public static void serializeArrayList(ArrayList<Task> list, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
