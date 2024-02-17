package remi.model.commands;

import java.util.ArrayList;
import remi.io.Message;
import remi.model.Task;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the find command. This finds a task from the TaskList and outputs its information.
 */
public class FindCommand implements Command {
    /**
     * This finds all tasks in the taskList that contains a given input in its label.
     *
     * @param args     the name of the tasks to be found
     * @param taskList the taskList object
     * @param chatbot  the chatbot object
     * @return the message to be outputted
     * @throws RemiError thrown if any known runtime errors occur
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        ArrayList<Task> tasks = taskList.getTask(args);
        TaskList foundTasks = new TaskList(tasks);
        return new Message("Here are the matching tasks in your list:\n" + foundTasks.toString());
    }
}
