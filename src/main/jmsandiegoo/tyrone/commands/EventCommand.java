package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.state.UndoState;
import jmsandiegoo.tyrone.task.Event;

/**
 * Represents the command to add event into task list.
 */
public class EventCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "event";
    private final Event eventItem;

    /**
     * @param eventItem - the event item to add into the list
     * */
    public EventCommand(Event eventItem) {
        this.eventItem = eventItem;
    }

    /**
     * Returns CommandResult after adding an event task into the list.
     *
     * @return CommandResult - the success result of the execution.
     */
    @Override
    public CommandResult execute() {
        UndoState undoState = new UndoState();
        undoState.setState(super.taskList);
        super.undoState = undoState;

        super.taskList.addItem(this.eventItem);
        return new CommandResult(
                String.format(Messages.MESSAGE_ADD_TASK, this.eventItem, super.taskList.getListSize()));
    }

    @Override
    public CommandResult undo() {
        super.taskList.restoreFromGivenList(super.undoState.getState());
        return new CommandResult(Messages.MESSAGE_EVENT_UNDO);
    }
}
