import java.io.IOException;

public class DeleteCommand extends Command {
    private final int indexToDelete;
    public DeleteCommand(String input) throws CommandException {
        input = input.trim();
        try {
            this.indexToDelete = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Delete expects the index of task to be deleted.");
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.deleteTask(this.indexToDelete));
        storage.save(taskList.toDataString());
    }
}
