package jmsandiegoo.tyrone.state;

import jmsandiegoo.tyrone.task.TaskList;

/**
 * Represents the previous state for undo feature.
 */
public class UndoState {
    private TaskList taskList;

    public TaskList getState() {
        return this.taskList;
    }

    public void setState(TaskList taskList) {
        this.taskList = taskList.copy();
    }
}
