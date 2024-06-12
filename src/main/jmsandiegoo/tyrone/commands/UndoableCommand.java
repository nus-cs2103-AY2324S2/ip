package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.state.UndoState;

/**
 * Represents commands that can be undone by the user.
 */
public abstract class UndoableCommand extends Command {
    protected UndoState undoState;

    /**
     * Returns command result after undoing the command.
     *
     * @return CommandResult.
     */
    public abstract CommandResult undo();
}
