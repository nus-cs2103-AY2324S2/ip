package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.ToDo;

/**
 * The AddToDoCommand class represents a command to add a to-do task to TaskList.
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a new AddToDoCommand object with the specified parameters.
     *
     * @param description The String description of the to-do task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ToDo toDo = new ToDo(this.description);
        taskList.addTask(toDo);
        ui.storeAddedMessage(toDo.toString(), taskList.getSize());
        storage.saveData(taskList);
    }
}
