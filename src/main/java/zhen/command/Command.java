package zhen.command;
import zhen.*;


/**
 * Used to represent the abstract concept of commands. Other specific commands will extend this
 * class to represent specific type of commands.
 */
public abstract class Command {
    /**
     * Executing specific command.
     *
     * @param taskList the List of tasks the command may work on
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Determine whether this command is an exit command
     *
     * @return true if the command is an exit command and false otherwise.
     */
    public abstract boolean isExit();
}
