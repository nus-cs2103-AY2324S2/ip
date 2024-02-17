package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.Deadline;
import Tasks.TaskList;

public class DeadlineCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public DeadlineCommand(String input, TaskList tasklist) {
        super("deadline <description> /by <date>", "deadline .* /by .*");
        this.input = input;
        this.taskList = tasklist;
    }

    public String execute() throws DudeException {
        boolean doesInputMatch = input.matches(this.getRegex());

        //only catches basic format errors. date format errors are caught by Deadline.from()
        if (!doesInputMatch) {
            throw new InvalidFormatException("Invalid format for deadline command. Please use this format: " + this.getFormat());
        }

        Deadline deadline = Deadline.from(input); //throws different DukeExceptions
        this.taskList.add_task(deadline); //throws TaskListFullException
        return "Got it. I've added this task:\n"
                + "\t  " + deadline.toString() + "\n"
                + "\tNow you have " + this.taskList.getSize() + " tasks in the list.";
    }
}
