package duke.command;

import duke.common.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = "todo: create an todo task.\n Example: todo SU 2103T";
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(this.description, false);
        taskList.addTask(todo);
        ui.showNewTask(todo, taskList);
    }
}
