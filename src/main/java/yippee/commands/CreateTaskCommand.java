package yippee.commands;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;
import yippee.tasks.Deadline;
import yippee.tasks.Event;
import yippee.tasks.Task;
import yippee.tasks.ToDo;

/**
 * Represents commands that creates a new task.
 */
public class CreateTaskCommand extends Command {
    private String taskType;
    private String details;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Task newTask = null;
        switch(taskType) {
        case "todo":
            newTask = new ToDo(details);
            break;
        case "deadline":
            String[] deadlineSplit = details.trim().split("/by");
            if (deadlineSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/by' to indicate the deadline!");
            }
            newTask = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
            break;
        case "event":
            String[] fromSplit = details.split("/from");
            if (fromSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/from' to indicate event start time!"
                );
            }
            String eventName = fromSplit[0].trim();

            String[] toSplit = fromSplit[1].split("/to");
            if (toSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/to' to indicate event end time!"
                );
            }
            String from = toSplit[0].trim();
            String to = toSplit[1].trim();
            newTask = new Event(eventName, from, to);
            break;
        default:
            assert false : taskType;
        }
        assert newTask != null : "New task created should not be null";
        tasks.addNewTask(newTask);
    }
}
