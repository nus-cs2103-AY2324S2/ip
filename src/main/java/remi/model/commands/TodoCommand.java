package remi.model.commands;

import remi.io.Message;
import remi.model.TaskList;
import remi.model.ToDo;
import remi.model.Ui;
import remi.parsing.Parser;
import remi.utils.RemiError;

/**
 * Command representing the creation of an todo.
 */
public class TodoCommand implements Command {
    /**
     * This function creates a new Deadline in the taskList.
     * It also returns a message confirming the todo is added and gives a summary of the new taskList.
     *
     * @param args the arguments for creating a Todo
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return the message confirming creation of the todo
     * @throws RemiError thrown if any options or label is missing
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        String label = Parser.getLabel(args);
        ToDo todo = new ToDo(label);
        taskList.addTask(todo);
        return new Message(String.format(
                "I've added the task.\n%s\nYou still have %d tasks in the list.",
                todo, taskList.size())
        );
    }
}
