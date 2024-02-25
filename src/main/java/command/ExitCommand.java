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
 * The ExitCommand class represents a command to exit the task management application.
 * It extends the Command class and implements the execute method to perform the exit operation.
 * Upon execution, it serializes the TaskList to the specified file path using object serialization,
 * and prints a farewell message to the user interface.
 *
 * @author wolffe88
 */

public class ExitCommand extends Command {

    /**
     * Executes the command by serializing the TaskList to the specified file path,
     * and prints a farewell message to the user interface.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        serializeArrayList(tasks.getList(), storage.getFilePath());
        return ("Bye. Hope to see you again soon!");

    }

    /**
     * Returns true to indicate that the program should exit when this command is executed.
     *
     * @return true, as the ExitCommand signifies the program's exit.
     */
    public boolean isExit() {
        return true;
    }


}
