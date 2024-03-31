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
    public String execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        String message;
        try {
            if (description.trim().isEmpty()) {
                throw new OrkException("To do what? You dumb meat!");
            }
            Task todo = new ToDos(description.trim());
            list.addTask(todo);
            int size = TaskList.list.size();
            storage.writeToDisk(list);
            message = ui.printAddedMessage(todo, size);
        } catch (OrkException exception) {
            message = exception.getMessage();
        }
        return message;
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
