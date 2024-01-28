import java.io.IOException;

public class MarkCommand extends Command {
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
            tasks.getTask(index).done();
            String[] messages = {
                "Nice! I've marked this task as done:", tasks.getTask(index).toString()
            };
            ui.show(messages);
            tasks.save();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid index.");
        }
    }
}


