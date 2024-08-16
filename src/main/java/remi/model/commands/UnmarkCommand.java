package remi.model.commands;

import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the functionality of the unmark command. This unmarks a task by index.
 */
public class UnmarkCommand implements Command {
    /**
     * Unmarks a task based on index
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return a Message confirming that the task has been unmarked
     * @throws RemiError if index is out of bounds
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        int idx = Integer.parseInt(args);
        taskList.unmarkTask(idx);
        return new Message("Why did you say it was finished then?\n" + taskList.getTask(idx));
    }
}
