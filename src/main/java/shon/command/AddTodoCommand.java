package shon.command;

import shon.TaskList;
import shon.Ui;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.print(tasks.addTodo(this.description));
    }
}
