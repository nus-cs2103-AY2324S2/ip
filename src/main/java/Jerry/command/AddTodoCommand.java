package Jerry.command;

import Jerry.TaskList;
import Jerry.ToDo;
import Jerry.Ui;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(Ui ui, TaskList tasks, String description) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.description = description;
    }

    @Override
    public String execute() {
        try {
            if (description.trim().isEmpty()) {
                throw new CommandFormatException("Error: Wrong format \nUsage: todo <task description>");
            }
            ToDo todo = new ToDo(description);
            tasks.addTask(todo);
            return ui.showAdded(todo, tasks);
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
