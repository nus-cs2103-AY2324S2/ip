package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

public class UnknownCommand extends Command{
    private String command;
    private String reason;
    public UnknownCommand(String command, String reason) {
        this.command = command;
        this.reason = reason;
    }
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return reason + "\nYour command was: " + command;
    }
}
