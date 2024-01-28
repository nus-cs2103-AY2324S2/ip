import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int indexToUnmark;
    public UnmarkCommand(String input) throws CommandException {
        input = input.trim();
        try {
            this.indexToUnmark = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Unmark expects the index of task to be unmarked.");
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.unmarkTask(this.indexToUnmark));
        storage.save(taskList.toDataString());
    }
}
