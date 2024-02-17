package dude.Commands;

import dude.Exceptions.DudeException;
import dude.Exceptions.InvalidFormatException;
import dude.Tasks.Task;
import dude.Tasks.TaskList;

public class MarkCommand extends Command {

    public static final String COMMAND_FORMAT = "mark <id>";
    private final String input;
    private final TaskList taskList;

    public MarkCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "mark \\d+");
        this.input = input.trim();
        this.taskList = tasklist;
    }

    @Override
    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("mark", COMMAND_FORMAT);
        }

        int id = Integer.parseInt(this.input.split(" ")[1]);

        //re-thow exception if task does not exist
        try {
            Task task = this.taskList.getTask(id);
            this.taskList.mark_as_done(id);

            String msg = "Nice! I've marked this task as done:\n"
                    + "\t  " + id + ". " + task.toString();

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
