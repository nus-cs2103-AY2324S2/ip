package junjie.commands;

import junjie.TaskList;
import junjie.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * The command word to list all tasks.
     */
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.toString();
    }
}
