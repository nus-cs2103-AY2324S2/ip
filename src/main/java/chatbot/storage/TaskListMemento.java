package chatbot.storage;

import chatbot.task.TaskList;

/**
 * This stores the internal state of a task list.
 * <p>
 * Memento is a behavioural design pattern that
 * lets you save and restore the previous state of an object
 * without revealing the details of its implementation.
 *
 * @author Titus Chew
 */
public final class TaskListMemento {
    /** Stores the saved list */
    private final TaskList savedTaskList;

    /**
     * Constructor for a memento.
     *
     * @param taskList The task list to store.
     */
    public TaskListMemento(TaskList taskList) {
        this.savedTaskList = new TaskList(taskList);
    }

    public TaskList getSavedState() {
        return savedTaskList;
    }
}
