package duke.commands;
import duke.utils.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class ListTaskCommand extends Command {
    public ListTaskCommand() {
        super(false);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList(ui);
    }
}
