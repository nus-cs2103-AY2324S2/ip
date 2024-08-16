package remi.model.commands;

import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the functionality of the marks command. This marks a task by index.
 */
public class MarkCommand implements Command {
    /**
     * Marks a task based on index
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return a Message confirming that the task has been marked
     * @throws RemiError if index is out of bounds
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        int idx = Integer.parseInt(args);
        taskList.markTask(idx);
        return new Message("Wow you took your time with that one:\n" + taskList.getTask(idx));
    }
}
