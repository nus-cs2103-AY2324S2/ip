package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.Task;
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

        int id = Integer.parseInt(this.input.split(" ")[1]);

        //re-throw exception if task does not exist
        try {
            Task task = this.taskList.getTask(id);

            this.taskList.remove_task(id);

            String msg = "Noted. I've removed this task:\n"
                    + "\t  " + id + ". " + task.toString() + "\n"
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
