import java.io.IOException;

public class UnmarkCommand extends Command {
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
            tasks.getTask(index).undone();
            String[] messages = {
                "OK, I've marked this task as not done yet:", tasks.getTask(index).toString()
            };
            ui.show(messages);
            tasks.save();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid index.");
        }
    }
}

