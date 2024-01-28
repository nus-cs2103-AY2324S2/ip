import java.io.IOException;

public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws DukeException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new DukeException("Please enter an index.");
        }
        String indexStr = input.getPositionalArgument(0);
        try {
            int index = Integer.parseInt(indexStr);
            if (!tasks.validIndex(index)) {
                throw new DukeException("Please enter a valid index.");
            }
            Task task = tasks.getTask(index);
            tasks.removeTask(index);
            String[] messages = {
                "Noted. I've removed this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
            };
            ui.show(messages);
            tasks.save();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid index.");
        }
    }
}

