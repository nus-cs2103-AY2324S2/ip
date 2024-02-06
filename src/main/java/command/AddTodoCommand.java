package command;
import duke.Ui;
import task.TaskList;
import task.Todo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Todo todo = new Todo (description);
        tasks.add(todo);
        ui.showAddTask(todo, tasks.size());
    }
}