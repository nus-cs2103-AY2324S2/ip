package dude.Commands;

import dude.Exceptions.DudeException;
import dude.Exceptions.InvalidFormatException;
import dude.Tasks.Event;
import dude.Tasks.TaskList;

public class EventCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public static final String COMMAND_FORMAT = "event <description> /from <date> /to <date>";

    public EventCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "event .* /from .* /to .*");
        this.input = input;
        this.taskList = tasklist;
    }

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
