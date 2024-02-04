package Jerry.command;

import Jerry.TaskList;
import Jerry.ToDo;
import Jerry.Ui;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(Ui ui, TaskList tasks, String description) {
        super(ui, tasks);
        this.description = description;
    }

    @Override
    public void execute() {
        try {
            if (description.trim().isEmpty()) {
                throw new CommandFormatException("Error: Wrong format \nUsage: todo <task description>");
            }
            ToDo todo = new ToDo(description);
            tasks.addTask(todo);
            ui.showAdded(todo, tasks);
        } catch (CommandFormatException e) {
            ui.showError(e.getMessage());
        }
    }
}
