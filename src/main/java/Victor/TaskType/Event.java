package victor.tasktype;

/**
 * The Event class is a child class of the Task class.
 * It inherits the variables from the Task Class, along with their
 * methods, and has overridden several methods in order to
 * add their own specific information to it.
 *
 * @author Dominic Fu Ming Jun
 */
public class Event extends Task {

    /**
     * The from variable is a String to indicate the start date and time of the event.
     */
    protected String from;

    /**
     * The to variable is a String to indicate the end date and time of the event.
     */
    protected String to;

    /**
     * The Event constructor takes in a String description and a boolean isDone
     * which is added to their respective variables of the same name.
     * This is done using the super keyword.
     * It also takes in 2 new inputs, from and to, which is used to indicate
     * the start and end date and timing of the event, and is added to
     * the variable of the same name.
     *
     * @param description The description of the task.
     * @param isDone      States whether the task is done or not.
     * @param from        The Start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * The toString method is overridden from the Task toString method to include
     * the additional information of [E] to indicate that this Task type is an
     * Event.
     *
     * @return A string that contains the extra information of the Event class,
     * along with the normal information displayed from the Task toString method.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from + "to: " + to + ")";
    }

    /**
     * The saveInput is overridden from the Task class. It is used to
     * write out all the data from the Event class in the format
     * of the data file before being saved into the data file.
     *
     * @return A string that is in the format to be added to the data file.
     */
    @Override
    public String saveInput() {
        return "E | " + isDone + " | " + description + " | " + from + " | " + to;
    }
}