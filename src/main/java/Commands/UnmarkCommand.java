package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidFormatException;
import Tasks.Task;
import Tasks.TaskList;

public class UnmarkCommand extends Command {

    public static final String COMMAND_FORMAT = "unmark <id>";
    private final String input;
    private final TaskList taskList;

    public UnmarkCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "unmark \\d+");
        this.input = input.trim();
        this.taskList = tasklist;
    }

    @Override
    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("unmark", COMMAND_FORMAT);
        }

        int id = Integer.parseInt(this.input.split(" ")[1]);

        //re-throw exception if task does not exist
        try {
            Task task = taskList.getTask(id);
            this.taskList.mark_as_undone(id);

            String msg = "Nice! I've unmarked this task as done:\n"
                    + "\t  " + id + ". " + task.toString();

            return msg;
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.UNMARK;
    }
}
