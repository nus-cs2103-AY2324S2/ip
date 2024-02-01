package Duke;

// Commands.Command interface
public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

