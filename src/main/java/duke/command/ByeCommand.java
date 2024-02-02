package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command{

    public ByeCommand(Parser.Cmd type) {
        super(type);
    }
    @Override
    public void run(TaskList taskList){
        Ui.leave();
    }
}
