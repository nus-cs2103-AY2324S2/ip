package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public class NoActionCommand extends Command {
    public NoActionCommand(Parser.Cmd type) {
        super(type);
    }
    @Override
    public void run(TaskList taskList) {
    }
}
