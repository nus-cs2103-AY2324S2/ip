package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.TaskList;
import Utils.CommandTypes;

public class DeleteCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public DeleteCommand(String input, TaskList tasklist) {
        super("delete <id>", "delete \\d+");
        this.input = input.trim();
        this.taskList = tasklist;
    }

    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("Invalid format for delete command. PLease use this format: " + this.getFormat());
        }

        int index = Integer.parseInt(this.input.split(" ")[1]);

        //re-throw exception if task does not exist
        try {
            this.taskList.remove_task(index);

            String msg = "Noted. I've removed this task:\n"
                    + "\t  " + index + ". " + this.taskList.getTask(index - 1).toString() + "\n"
                    + "\tNow you have " + this.taskList.getSize() + " tasks in the list.";

            return msg;
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.DELETE;
    }
}
