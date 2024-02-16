package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.state.UndoState;
import jmsandiegoo.tyrone.task.Deadline;

/**
 * Represents the command to add deadline tasks to the list.
 */
public class DeadlineCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline deadlineItem;

    /**
     * @param deadlineItem - the deadline task to be added by the command.
     */
    public DeadlineCommand(Deadline deadlineItem) {
        this.deadlineItem = deadlineItem;
    }

    /**
     * Returns CommandResult after adding the deadline into the list.
     *
     * @return CommandResult the success result of the execution.
     */
    @Override
    public CommandResult execute() {
        UndoState undoState = new UndoState();
        undoState.setState(super.taskList);
        super.undoState = undoState;

        super.taskList.addItem(this.deadlineItem);
        return new CommandResult(
                String.format(Messages.MESSAGE_ADD_TASK, this.deadlineItem, super.taskList.getListSize()));
    }

    @Override
    public CommandResult undo() {
        super.taskList.restoreFromGivenList(super.undoState.getState());
        return new CommandResult(Messages.MESSAGE_DEADLINE_UNDO);
    }
}
