package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;
import yippee.tasks.Deadline;
import yippee.tasks.Event;
import yippee.tasks.Task;
import yippee.tasks.ToDo;

import java.util.Arrays;

/**
 * Represents commands that creates a new task.
 */
public class CreateTaskCommand extends Command {
    private String taskType;
    private String details;
    private static int totalCreated = 0;
    public CreateTaskCommand(String taskType, String details) {
        super(false);
        this.taskType = taskType;
        this.details = details;
    }

    /**
     * {@inheritDoc}
     * @param tasks TaskList of active tasks
     * @param ui Ui instance to print responses.
     * @param storage Storage instance to store any data.
     * @throws InvalidCommandException If command is of invalid format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Task newTask = null;
        switch(taskType) {
        case "todo":
            newTask = new ToDo(details);
            break;
        case "deadline":
            String[] deadlineSplit = processDeadline();
            newTask = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
            break;
        case "event":
            String[] params = processEvent();
            newTask = new Event(params[0], params[1], params[2]);
            break;
        default:
            assert false : taskType;
        }
        assert newTask != null : "New task created should not be null";
        totalCreated++;
        return tasks.addNewTask(newTask);
    }

    public static int getTotalCreated() {
        return totalCreated;
    }

    private String[] processDeadline() throws InvalidCommandException {
        String[] deadlineSplit = details.trim().split("/by");
        if (deadlineSplit.length == 1) {
            throw new InvalidCommandException(
                    "Invalid format >:( Make sure you used '/by' to indicate the deadline!");
        }
        Arrays.setAll(deadlineSplit, i -> deadlineSplit[i].trim());
        return deadlineSplit;
    }

    private String[] processEvent() throws InvalidCommandException {
        String[] result = new String[3];

        String[] fromSplit = details.split("/from");
        if (fromSplit.length == 1) {
            throw new InvalidCommandException(
                    "Invalid format >:( Make sure you used '/from' to indicate event start time!"
            );
        }

        String[] toSplit = fromSplit[1].split("/to");
        if (toSplit.length == 1) {
            throw new InvalidCommandException(
                    "Invalid format >:( Make sure you used '/to' to indicate event end time!"
            );
        }
        result[0] = fromSplit[0].trim();
        result[1] = toSplit[0].trim();
        result[2] = toSplit[1].trim();

        return result;
    }
}
