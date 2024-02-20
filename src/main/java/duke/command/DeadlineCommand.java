package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.FormatException;
import duke.utils.Parser;

/**
 * Class to run Deadline Command.
 *
 * @author KohGuanZeh
 */
public class DeadlineCommand extends Command {

    private final Deadline deadline;

    /**
     * Creates a Command that runs to add a Deadline task.
     *
     * @param input Input of Deadline information.
     * @throws CommandException Exception to inform user of formatting error in input.
     */
    public DeadlineCommand(String input) throws CommandException {
        try {
            this.deadline = Parser.parseDeadlineInput(input);
        } catch (FormatException e) {
            throw new CommandException("Error. Unable to create task.\nFormat: " + Deadline.INPUT_DEADLINE_FORMAT);
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.addTask(this.deadline);
        storage.save(taskList.toDataString());
        return message;
    }
}
