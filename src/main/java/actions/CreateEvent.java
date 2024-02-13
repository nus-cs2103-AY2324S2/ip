package actions;

import tasks.Event;
import ui.Duke;


/**
 * The `CreateEvent` class implements the Action interface and represents
 * an action to create a new Event task.
 * It stores the description, start time, and end time for the new Event task
 * and provides a method to execute the action.
 */
public class CreateEvent implements Action {

    private String desc;
    private String from;
    private String to;

    /**
     * Constructs a `CreateEvent` object with the specified description, start time, and end time.
     *
     * @param desc The description of the new Event task.
     * @param from The start time of the new Event task.
     * @param to   The end time of the new Event task.
     */
    public CreateEvent(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the create event action by creating a new Event task
     * with the stored description, start time, and end time.
     * The new task is then added to the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the createevent action.
     */
    @Override
    public String execute(Duke bot) {
        Event e = new Event(desc, from, to);
        bot.getTaskList().addToList(e);
        return ("Event successfully added!");
    }
}
