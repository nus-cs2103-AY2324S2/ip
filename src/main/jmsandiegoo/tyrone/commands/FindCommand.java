package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.task.Event;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(
                String.format(Messages.MESSAGE_FIND,
                        super.taskList.findItemsByKeyword(this.keyword))
        );
    }
}
