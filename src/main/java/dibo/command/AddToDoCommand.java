package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.ToDo;

/**
 * Class to handle the command which adds a to-do task.
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructor for the AddToDoCommand class.
     *
     * @param description The description of the to-do task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Run the add to-do task command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the added message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when trying to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ToDo toDo = new ToDo(this.description);
        taskList.addTask(toDo);
        ui.showAdded(toDo.toString(), taskList.getSize());
        storage.saveData(taskList);
    }
}
