import java.io.IOException;

public class MarkCommand extends Command {
    private final String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            int index = Integer.parseInt(fullCommand.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.getTasks().get(index);
            ui.showMarkedMessage(task);
            task.markAsDone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number to unmark.");
        }
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
