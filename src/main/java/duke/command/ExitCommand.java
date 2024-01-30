package duke.command;

import duke.state.ProgramState;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("");
    }

    public String execute(TaskList list, ProgramState state) {
        String response = "Cya!";
        state.setExit();
        return response;
    }
}
