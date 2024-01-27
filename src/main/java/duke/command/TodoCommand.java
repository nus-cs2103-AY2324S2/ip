package duke.command;

import duke.common.TaskList;
import duke.storage.Storage;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a Todo command initiated by the user. <code>TodoCommand</code> would add an new todo task to the TaskList.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = "todo: create an todo task.\n Example: todo SU 2103T";
    private String description;

    /**
     * Constructor of the TodoCommand
     * @param description Description of the Todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task to the TaskList and show the result to the users using ui
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(this.description, false);
        taskList.addTask(todo);


        ui.showNewTask(todo, taskList);
    }
}
