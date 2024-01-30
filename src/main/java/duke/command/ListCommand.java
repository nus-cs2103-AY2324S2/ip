package duke.command;

import duke.state.ProgramState;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super("");
    }

    public String execute(TaskList list, ProgramState state) {
        state.setNormal();
        return list.toString();
    }
}
