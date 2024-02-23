package actions;

import static actions.CheckDuplicate.checkDuplicate;
import exceptionhandling.DukeException;
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
     * @param command     The command string.
     */
    public CreateEvent(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length <= 1) {
            throw new DukeException("Please write a description and the time period for your task!");
        }
        String[] infoSplit = splitCommand[1].split("/from ", 2);
        if (infoSplit.length <= 1) {
            throw new DukeException("Please include a time period by using from and to keyword such as"
                    + "'/from today /to tomorrow");
        }
        String[] infoSplit2 = infoSplit[1].split("/to ", 2);
        if (infoSplit2.length <= 1) {
            throw new DukeException("Please include a time period by using from and to keyword such as"
                    + "'/from today /to tomorrow");
        }
        String eventDesc = infoSplit[0];
        String from = infoSplit2[0];
        String to = infoSplit2[1];
        this.desc = eventDesc;
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
        assert(bot != null);
        assert(bot.getTaskList() != null);
        if (checkDuplicate(bot, this.desc, "E")) {
            return ("There is already an event with the same name!");
        }
        Event e = new Event(desc, from, to);
        bot.getTaskList().addToList(e);
        return ("Event successfully added!");
    }
}
