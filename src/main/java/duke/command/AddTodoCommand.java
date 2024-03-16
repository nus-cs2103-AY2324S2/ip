package duke.command;

import duke.task.Todo;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command of adding a new todo to a task list.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Initializes a Command to add a todo using the input description.
     *
     * @param type the type of the command which is todo.
     * @param description the description of the todo task to be added.
     */
    public AddTodoCommand(Parser.Cmd type, String description) {
        super(type);
        this.description = description;
    }

    /**
     * Adds a new todo task to give taskList.
     *
     * @param taskList the given taskList to add the task to.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        Todo todo = new Todo(this.description);
        String[] data = {this.description};
        taskList.addTask(todo, "todo", data);
        ui.informItemAdded(todo, taskList);
    }
}
