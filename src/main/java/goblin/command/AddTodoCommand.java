package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.exception.OrkException;
import goblin.task.ToDos;
import goblin.task.Task;

public class AddTodoCommand extends Command {
    protected String description;

    /**
     * add the ToDos to the list
     * @param list the list of tasks
     * @param ui handle ui
     * @param storage handle storage
     * @throws OrkException when the description is not complete
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        try {
            if (description.trim().isEmpty()) {
                throw new OrkException("To do what? You dumb meat!");
            }
            Task todo = new ToDos(description.trim());
            list.addTask(todo);
            int size = TaskList.list.size();
            ui.printAddedMessage(todo, size);
            storage.writeToDisk(list);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }

    /**
     * create a new AddTodoCommand
     * @param description for the todos object
     */
    public AddTodoCommand(String description) {
        super();
        this.description = description;
    }
}
