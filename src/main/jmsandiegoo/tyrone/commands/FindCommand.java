package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;

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
        return new CommandResult(
                String.format(Messages.MESSAGE_FIND,
                        super.taskList.findItemsByKeyword(this.keyword))
        );
    }
}
