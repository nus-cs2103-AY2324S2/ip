package task;

import duke.ProgramState;

public class ListCommand extends Command {
    public ListCommand() {
        super("");
    }

    public String execute(TaskList list, ProgramState state) {
        state.setNormal();
        return list.toString();
    }
}
