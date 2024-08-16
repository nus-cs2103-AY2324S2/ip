package remi.model.commands;

import remi.io.Message;
import remi.model.Task;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the delete command. This deletes a task from the TaskList.
 */
public class DeleteCommand implements Command {
    /**
     * This deletes a task from the taskList.
     * The index given should be 1-indexed. After deletion, a confirmation message is returned along with a summary of
     * the taskList.
     *
     * @param args     the index of the task to be deleted
     * @param taskList the taskList object
     * @param chatbot  the chatbot object
     * @return a message confirming the deletion
     * @throws RemiError thrown if the index is out of bounds
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        int idx = Integer.parseInt(args);
        Task task = taskList.getTask(idx);
        taskList.removeTask(idx);
        return new Message(String.format("I've removed the task.\n%s\nYou still have %d tasks in the list.",
                task, taskList.size())
        );
    }
}
