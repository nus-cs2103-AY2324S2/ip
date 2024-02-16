package jmsandiegoo.tyrone.state;

import jmsandiegoo.tyrone.commands.CommandResult;
import jmsandiegoo.tyrone.commands.UndoableCommand;
import jmsandiegoo.tyrone.task.TaskList;

import java.util.Stack;

/**
 * Represents the class the manages the command history
 * stack for undo commands.
 */
public class StateManager {
    private final TaskList taskList;
    private final Stack<UndoableCommand> commandStack;

    public StateManager() {
        this.taskList = new TaskList();
        this.commandStack = new Stack<>();
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Adds undoable command into the history stack for undo feature.
     *
     * @param command - the undoable command entered by the user.
     */
    public void addCommandToStack(UndoableCommand command) {
        commandStack.push(command);
    }

    /**
     * Returns command result after undoing the most recent
     * command from the stack.
     *
     * @return CommandResult - the result output of the command undo.
     */
    public CommandResult popCommandFromStack() {
        if (this.commandStack.isEmpty()) {
            return new CommandResult("No previous commands left to undo.");
        }

        UndoableCommand command = commandStack.pop();
        return command.undo();
    }
}
