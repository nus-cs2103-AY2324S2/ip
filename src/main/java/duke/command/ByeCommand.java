package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command{
    public ByeCommand(){
        super(1);
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        ui.showBye();
    }
}
