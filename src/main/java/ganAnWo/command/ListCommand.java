package ganAnWo.command;

import ganAnWo.Storage;
import ganAnWo.TaskList;
import ganAnWo.Ui;

/**
 * Class for "list" command.
 *
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     */
    public ListCommand() {
        super(0);
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        String out = tL.showList();
        ui.showMessage(out);
        setOut(out);
    }
}
