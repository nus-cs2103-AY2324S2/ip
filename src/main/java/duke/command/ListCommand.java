package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    /**
     * Constructor for ListCommand.
     *
     */
    public ListCommand(){
        super(0);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        String out = tl.showList();
        ui.showMessage(out);
    }
}
