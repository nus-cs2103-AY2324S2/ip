package commands;

import destiny.DukeException;
import destiny.Event;
import destiny.TaskList;

/**
 * Command that creates new Event task and adds it to the task list.
 */
public class EventCmd extends Command {
    private String details;

    /**
     * Constructor for the EventCmd class.
     *
     * @param details Information for the Deadline task.
     */
    public EventCmd(String details) {
        this.details = details;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (details == "" || details == null) {
            throw new DukeException("Please enter a description for the Event command");
        }

        String[] splitDetails = details.split("/from ", 2);
        String[] secondSplitDetails = new String[2];
        try {
            secondSplitDetails = splitDetails[1].split("/to ", 2);
            try {
                Event newEvent = new Event(splitDetails[0], secondSplitDetails[0], secondSplitDetails[1]);
                return tasks.addTask(newEvent);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("After entering your desired start time,\n"
                        + "add '/to' followed by your desired end time");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("After entering the event task name,\n"
                    + "add '/from' followed by your desired start time");
        }
    }
}
