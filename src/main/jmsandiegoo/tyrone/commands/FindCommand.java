package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.task.TaskList;

/**
 * Represents the command to find a task with a keyword
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * @param keyword to use in finding task containing it.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns CommandResult containing the items with the keyword.
     *
     * @return CommandResult - the success result of the execution.
     */
    @Override
    public CommandResult execute() {

        TaskList resTaskList = super.taskList.findItemsByKeyword(this.keyword);

        if (resTaskList.getListSize() > 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_FIND,
                            resTaskList));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_FIND, "No items found with the specified keyword"));
        }
    }
}