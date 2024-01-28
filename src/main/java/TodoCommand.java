import java.io.IOException;

public class TodoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws DukeException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new DukeException("Please enter the task description.");
        }
        String description = input.getPositionalArgument(0);
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showAddedTask(task, tasks);
        tasks.save();
    }
}
