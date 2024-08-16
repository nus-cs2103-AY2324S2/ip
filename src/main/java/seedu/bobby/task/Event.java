package seedu.bobby.task;

import seedu.bobby.BobbyException;

/**
 * <h1> Event </h1>
 * This Event class is a subclass of the Task class, holding all the attributes and methods
 * of its parent class. This program also has methods that overrides the methods from its parent class Task,
 * to handle the cases differently when the task is an event and more specific than a normal todo task.
 * Event class objects have an attribute from and to which represents the start and end timing of the
 * event respectively. This class handles all the functionalities of an Event object, such as the
 * printing of the task description and the function that returns the data to be stored.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Event extends Task{
    private String from;
    private String to;
    public Event(String event, String from, String to) {
        super(event);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String getTag() {
        return "E";
    }

    /**
     * Updates the task attribute of the event object specified to the new information
     * @param attribute String of the task attribute to be updated, possible attributes for event task
     *                  are description, from (start timing) and to (end timing).
     * @param toUpdate String of the new information to be updated
     * @throws BobbyException
     */
    @Override
    public void update(String attribute, String toUpdate) throws BobbyException {
        if (attribute.equalsIgnoreCase("desc")) {
            description = toUpdate.trim();
        } else if (attribute.equalsIgnoreCase("from")) {
            from = toUpdate.trim();
        } else if (attribute.equalsIgnoreCase("to")) {
            to = toUpdate.trim();
        } else {
            throw new BobbyException("Oops. The task you want to update does not have a " + attribute + " attribute.");
        }
    }

    /**
     * Returns a string that contains information of the event task to be stored in the hard disk.
     * Used as an argument in Storage class to be written into the file.
     *
     * @return String that represents information of the Deadlines object.
     */
    @Override
    public String toStore() {
        return " E | " + (isDone ? "1" : "0") +  " | "  + description + " | " + from
                    + " | " + to + "\n";
    }

    /**
     * Returns a String of the full task description. Called in TaskList's printList method,
     * called for each task in the list.
     * @param num the number of the task in the task list
     * @return String of the task description of the Deadline object.
     */
    @Override
    public String printTaskDesc(int num){
        assert num >= 1 : "task number should be more than or equals to 1";
        if (num == 1) {
            return String.format("Okies~ Here are the tasks in your list:\n %d.[%s][%s] %s (from: %s to: %s)\n",
                    num, getTag(), getStatusIcon(), getDescription(), from, to);
        } else {
            return String.format(" %d.[%s][%s] %s (from: %s to: %s)\n",
                    num, getTag(), getStatusIcon(), getDescription(), from, to);
        }
    }

    /**
     * Returns a String of the description with the number of the matching task.
     * Called by the Storage findFromFile method.
     * @param num number of the task in the matching task list.
     * @return a String of the matching task description with the specified task number
     */
    @Override
    public String printMatchDesc(int num) {
        assert num >= 1 : "task number should be more than or equals to 1";
        return String.format(" %d.[%s][%s] %s (from: %s to: %s)\n",
                num, getTag(), getStatusIcon(), getDescription(), from, to);
    }

    /**
     * Returns a string of the full description of task without any specified task number.
     * Called when updating, adding or deleting tasks to show the user the details of the task that
     * has been added, updated or deleted.
     * @return String of the full description of the task
     */
    @Override
    public String printFullDesc() {
        return String.format(" [%s][%s] %s (from: %s to: %s)\n",
                getTag(), getStatusIcon(), getDescription(), from, to);
    }
}
