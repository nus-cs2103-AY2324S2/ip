package jav.command;

/**
 * Command handles the execution of a given command by the user.
 */
public abstract class Command {
    public abstract void Execute() throws Exception;
}
