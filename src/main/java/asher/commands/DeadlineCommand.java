package asher.commands;

import asher.BotException;
import asher.tasks.Deadline;
import asher.tasks.TaskList;
import asher.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a command to create a deadline task.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a Deadline Command object with the given input string.
     *
     * @param input The input string for the Deadline Command.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Parses the input string to create a Deadline object.
     *
     * @return The created Deadline object.
     * @throws BotException BotException is thrown if due date and description is invalid, or if the input format is wrong.
     */
    public Deadline createDeadlineCommand() throws BotException {
        if (input.length() < 10) {
            throw new BotException("Deadline description not found!");
        }
        int split = input.indexOf("/by");
        if (split == -1) {
            throw new BotException("Due date not found!");
        }
        if (split + 4 >= input.length()) {
            throw new BotException("No such deadline!");
        }

        String[] parts = input.substring(9).split("/by", 2);
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        String[] deadlineParts = deadline.split(" ", 2);
        String dueDateInString = deadlineParts[0].trim();
        String dueTimeInString = deadlineParts[1].trim();
        LocalDate dueDate = LocalDate.parse(dueDateInString);
        LocalTime dueTime = LocalTime.parse(dueTimeInString);

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description and deadline cannot be empty!");
        }
        return new Deadline(description, dueDate, dueTime);
    }

    /**
     * Executes the Deadline Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Deadline Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Deadline deadline = createDeadlineCommand();
            taskList.addTask(deadline);
            String addTaskMessage = ui.showAddTaskMessage(deadline);
            int totalTask = taskList.getSize();
            String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(totalTask);

            return addTaskMessage + "\n" + numberOfTaskMessage;
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
