package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class to run Deadline Command.
 *
 * @author KohGuanZeh
 */
public class DeadlineCommand extends Command {

    private final String deadlineDescription;
    private final LocalDateTime deadlineBy;

    /**
     * Creates a Command that runs to add a Deadline task.
     *
     * @param input Input of Deadline information.
     * @throws CommandException Exception when there is a formatting error in the input.
     */
    public DeadlineCommand(String input) throws CommandException {
        input = input.trim();
        try {
            String[] tokens = input.split("/by");
            this.deadlineDescription = tokens[0].trim();
            if (this.deadlineDescription.isEmpty()) {
                throw new CommandException("Error. Unable to create task.\nFormat: "
                        + Deadline.CREATE_DEADLINE_FORMAT);
            }
            this.deadlineBy = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new CommandException("Error. Unable to create task.\nFormat: "
                    + Deadline.CREATE_DEADLINE_FORMAT);
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.addTask(new Deadline(this.deadlineDescription, deadlineBy)));
        storage.save(taskList.toDataString());
    }
}
