package junjie.commands;

import junjie.TaskList;
import junjie.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * The command word to exit the program.
     */
    public static final String COMMAND_WORD = "bye";
    private static final String MESSAGE = "ok see you bro";

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return MESSAGE;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
