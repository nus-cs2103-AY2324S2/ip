package jerry.command;

import jerry.TaskList;
import jerry.ToDo;
import jerry.Ui;

public class AddTodoCommand extends Command {
    private final String description;

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
