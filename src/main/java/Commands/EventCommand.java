package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.TaskList;
import Utils.CommandTypes;

public class EventCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public EventCommand(String input, TaskList tasklist) {
        super("event <description> /from <date> /to <date>", "event .* /from .* /to .*");
        this.input = input;
        this.taskList = tasklist;
    }

    public String execute() throws DudeException {
        boolean doesInputMatch = input.matches(this.getRegex());

        //only catches basic format errors. date format errors are caught by Deadline.from()
        if (!doesInputMatch) {
            throw new InvalidFormatException("Invalid format for deadline command. Please use this format: " + this.getFormat());
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
