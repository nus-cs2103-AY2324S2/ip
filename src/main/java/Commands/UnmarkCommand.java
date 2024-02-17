package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.TaskList;

public class UnmarkCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public UnmarkCommand(String input, TaskList tasklist) {
        super("mark <id>", "unmark \\d+");
        this.input = input.trim();
        this.taskList = tasklist;
    }

    @Override
    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("Invalid format for mark command. Please use this format: " + this.getFormat());
        }

        int index = Integer.parseInt(this.input.split(" ")[1]);

        //re-throw exception if task does not exist
        try {
            this.taskList.mark_as_undone(index);

            String msg = "Nice! I've unmarked this task as done:\n"
                    + "\t  " + index + ". " + this.taskList.getTask(index - 1).toString();

            return msg;
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }
}
