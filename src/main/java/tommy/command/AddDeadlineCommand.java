package tommy.command;

import tommy.Parser;
import tommy.Storage;
import tommy.Ui;
import tommy.exception.InvalidArgumentException;
import tommy.task.Deadline;
import tommy.task.Task;
import tommy.task.TaskList;

/**
 * Represents the command to add a Deadline task to the taskList.
 */
public class AddDeadlineCommand extends Command {
    private String description;

    /**
     * Constructor for an AddDeadline command,
     * which initialises the command with its task description.
     *
     * @param description Description of the Deadline task.
     */
    public AddDeadlineCommand(String description) {
        this.description = description.strip();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            String[] components = this.description.split(" /by ", 2);
            String deadlineDetail = components[0];
            String byDate = components[1];

            String formattedByDate = Parser.formatDate(byDate);
            String formattedDescription = deadlineDetail + " (by: " + formattedByDate + ")";

            boolean isDuplicate = taskList.isADuplicateTask(taskList, formattedDescription);
            if (isDuplicate) {
                throw new InvalidArgumentException("DEADLINE - There is already an identical task");
            }

            Task deadline = new Deadline(formattedDescription);

            taskList.addTask(deadline);
            Storage.save(taskList);
            return ui.displayNewTask(deadline, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("DEADLINE");
        }
    }
}
