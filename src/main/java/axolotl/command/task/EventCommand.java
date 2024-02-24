package axolotl.command.task;

import axolotl.command.CommandEnum;
import axolotl.command.CommandResult;
import axolotl.task.Event;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class EventCommand extends TaskCommand {
    public static final String COMMAND = "event";
    public static final Pattern ARG_FORMAT = Pattern.compile("(?<task>.+?) /from (?<from>.+) /to (?<to>.+)");
    public static final String INVALID_COMMAND = "-------------------------------- \n" +
            "Oops, wrong format! Please follow this format for event task entries: \n" +
            CommandEnum.EVENT.COMMAND_SIGNATURE + "\n" +
            " (e.g. event team project meeting /from 11/10/2019 1800 /to 11/10/2019 2000 ) \n" +
            "-------------------------------- \n";
    private final Event t;

    public EventCommand(String task, LocalDateTime from, LocalDateTime to) {
        this.t = new Event(task, false, from, to);
    }
    @Override
    public CommandResult execute() {
        storage.addTask(t);
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), storage.getNumOfTasks()));
    }
}
