package duke.command.task;


import duke.command.Command;

public abstract class TaskCommand extends Command {
    protected final String COMMAND_SUCCESS =  "-------------------------------- \n" +
            "Sure, I've added this task: \n" +
            "%s \n" +
            "Now you have %d task(s) in the list. \n" +
            "-------------------------------- \n";

    public static final String COMMAND_INVALID_DATETIME = "-------------------------------- \n" +
            "Oops, wrong datetime format! \n" +
            "Please follow this format <dd/MM/yyyy HHmm> (e.g. 02/12/2019 1800) \n" +
            "-------------------------------- \n";
}
