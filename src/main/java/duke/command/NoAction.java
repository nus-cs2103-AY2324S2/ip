package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public class NoAction extends Command{
    public NoAction(Parser.Cmd type) {
        super(type);
    }
    @Override
    public void run(TaskList taskList) {
    }
}
