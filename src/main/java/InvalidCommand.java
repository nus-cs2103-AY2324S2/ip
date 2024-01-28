import java.io.IOException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws DukeException, IOException {
        ui.show("Sorry I can't help with that :(");
    }
}


