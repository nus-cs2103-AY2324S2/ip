package junjie.commands;

import junjie.TaskList;
import junjie.Ui;

/**
 * Represents a command that is not recognised by the chat bot.
 */
public class DefaultCommand extends Command {
    private static final String MESSAGE = "what are u saying";

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return MESSAGE;
    }
}
