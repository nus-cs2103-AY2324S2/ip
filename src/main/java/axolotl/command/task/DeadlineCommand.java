package axolotl.command.task;

import axolotl.command.CommandEnum;
import axolotl.command.CommandResult;
import axolotl.task.Deadline;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class DeadlineCommand extends TaskCommand {
    public static final String COMMAND = "deadline";
    public static final String INVALID_COMMAND = "-------------------------------- \n" +
            "Oops, wrong format! Please follow this format for deadline task entries: \n" +
            CommandEnum.DEADLINE.COMMAND_SIGNATURE + "\n" +
            "(e.g. deadline submit report /by 11/10/2019 1800 ) \n" +
            "-------------------------------- \n";
    public static final Pattern ARG_FORMAT = Pattern.compile("(?<task>.+?) /by (?<by>.+)");


    private final Deadline t;

    public DeadlineCommand(String task, LocalDateTime by) {
        this.t = new Deadline(task, false, by);
    }
    @Override
    public CommandResult execute() {
        storage.addTask(t);
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), storage.getNumOfTasks()));
    }
}
