package duke.command.task;

import duke.command.CommandResult;
import duke.task.Event;

public class EventCommand extends TaskCommand {
    public static final String COMMAND = "deadline";
    private final Event t;

    public EventCommand(Event t) {
        this.t = t;
    }
    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), -1));
    }
}
