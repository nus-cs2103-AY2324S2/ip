package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    public ListCommand(){
        super(0);
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        String out = tL.showList();
        ui.showMessage(out);
    }
}
