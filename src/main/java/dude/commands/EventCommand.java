package dude.commands;

import dude.exceptions.DudeException;
import dude.exceptions.InvalidFormatException;
import dude.tasks.Event;
import dude.tasks.TaskList;

/**
 * The EventCommand class represents a command that adds an event to the TaskList object.
 */
public class EventCommand extends Command {

    public static final String COMMAND_FORMAT = "event <description> /from <date> /to <date>";

    private final String input;
    private final TaskList taskList;

    /**
     * Constructor for the EventCommand class. Returns a command object to add an event to the TaskList object upon
     * execution.
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param tasklist The TaskList object to which the event is to be added.
     */
    public EventCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "event .* /from .* /to .*");

        assert(input != null);
        assert(tasklist != null);
        assert(input.contains("event"));


        this.input = input;
        this.taskList = tasklist;
    }

    /**
     * Add an event to the TaskList object.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    public String execute() throws DudeException {
        boolean doesInputMatch = input.matches(this.getRegex());

        //only catches basic format errors. date format errors are caught by Deadline.from()
        if (!doesInputMatch) {
            throw new InvalidFormatException("event", COMMAND_FORMAT);
        }

        Event event = Event.from(input); //throws different DukeExceptions
        this.taskList.add_task(event); //throws TaskListFullException
        return "Got it. I've added this task:\n"
                + "\t  " + event.toString() + "\n"
                + "\tNow you have " + this.taskList.getSize() + " tasks in the list.";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.EVENT;
    }

}
