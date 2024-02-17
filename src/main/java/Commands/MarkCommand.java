package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.TaskList;
import Utils.CommandTypes;

public class MarkCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public MarkCommand(String input, TaskList tasklist) {
        super("mark <id>", "mark \\d+");
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

        //re-thow exception if task does not exist
        try {
            this.taskList.mark_as_done(index);

            String msg = "Nice! I've marked this task as done:\n"
                    + "\t  " + index + ". " + this.taskList.getTask(index - 1).toString();

            return msg;
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.MARK;
    }
}
