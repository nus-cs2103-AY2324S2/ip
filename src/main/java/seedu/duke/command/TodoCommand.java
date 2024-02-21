package seedu.duke.command;

import seedu.duke.common.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.ui.Ui;


/**
 * Represents a Todo command initiated by the user. <code>TodoCommand</code> would add an new todo task to the TaskList.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = "todo: create an todo task.\n Example: todo SU 2103T";
    private String description;

    /**
     * Constructor of the TodoCommand
     *
     * @param description Description of the Todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task to the TaskList and show the result to the users using ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DuplicateTaskException {
        Todo todo = new Todo(this.description, false);
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Todo) {
                boolean isSameDescription = task.getDescription().equals(description);
                boolean isSameStatus = !task.getHasDone();
                if (isSameDescription && isSameStatus) {
                    throw new DuplicateTaskException(task);
                }
            }
        }
        taskList.addTask(todo);
        ui.generateNewTaskResponse(todo, taskList);
    }
}
