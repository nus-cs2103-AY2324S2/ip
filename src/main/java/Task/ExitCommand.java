package task;

import duke.ProgramState;

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
