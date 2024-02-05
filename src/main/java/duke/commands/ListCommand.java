package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidCommandException;

public class ListCommand extends Command {
    private boolean isExit = false;
    public ListCommand() {
        super(false);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        tasks.printList();
    }
}
