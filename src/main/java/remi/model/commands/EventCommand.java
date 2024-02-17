package remi.model.commands;

import remi.io.Message;
import remi.model.Event;
import remi.model.TaskList;
import remi.model.Ui;
import remi.parsing.Parser;
import remi.utils.RemiError;

/**
 * Command representing the creation of an event.
 */
public class EventCommand implements Command {
    /**
     * This function creates a new Event in the taskList.
     * It also returns a message confirming the event is added and gives a summary of the new taskList.
     *
     * @param args     the arguments for creating an Event
     * @param taskList the taskList object
     * @param chatbot  the chatbot object
     * @return the message confirming creation of the event
     * @throws RemiError thrown if any options or label is missing
     */
    @Override
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        String label = Parser.getLabel(args);
        String from = Parser.findOption("/from", args);
        String to = Parser.findOption("/to", args);
        Event event = new Event(label, from, to);
        taskList.addTask(event);
        return new Message(String.format("I've added the task.\n%s\nYou still have %d tasks in the list.",
                event, taskList.size())
        );
    }
}
