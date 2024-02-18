package duke.command.task;


import duke.command.Command;

public abstract class TaskCommand extends Command {
    protected final String COMMAND_SUCCESS =  "-------------------------------- \n" +
            "Sure, I've added this task: \n" +
            "%s \n" +
            "Now you have %d task(s) in the list. \n" +
            "-------------------------------- \n";
}
