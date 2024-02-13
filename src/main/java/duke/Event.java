package duke;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Creates an Event task with the given description, start, and end details.
     *
     * @param description The description of the task.
     * @param start       The start details of the event.
     * @param end         The end details of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Adds an Event task to the task list with the specified description, start, and end details.
     *
     * @param taskList     The task list to add the task to.
     * @param description  The description of the task.
     * @param start        The start details of the event.
     * @param end          The end details of the event.
     * @throws DukeException If there is an issue adding the task.
     */
    public static void addEventTask(TaskList taskList, String description, String start, String end) throws DukeException {
        taskList.addTask(new Event(description, start, end));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.size() - 1).getStatusIcon());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    /**
     * Returns the string representation of the task's status and details.
     *
     * @return The formatted string representation.
     */
    @Override
    public String getStatusIcon() {
        return "[E] " + super.getStatusIcon() + " " + description + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return The formatted string for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
