package duke;
import java.util.List;

/**
 * Represents a command that can be executed by the Duke program.
 */
public class Command {
    public enum CommandType {
        BYE, LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, FIND, INVALID

    }
    /**
     * Enumerates the possible types of commands.
     */
    private CommandType type;
    private String argument;

    /**
     * Constructs a Command object with the specified type and argument.
     *
     * @param type     The type of the command.
     * @param argument The argument associated with the command.
     */

    public Command(CommandType type, String argument) {
        this.type = type;
        this.argument = argument;
    }

    /**
     * Executes the command based on its type.
     *
     * @param tasks   The TaskList on which the command operates.
     * @param ui      The Ui used to interact with the user.
     * @param storage The Storage used to save and load tasks.
     * @throws DukeException If there is an error executing the command.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (type) {
        case BYE:
            ui.showGoodbye();
            break;
        case LIST:
            ui.showTaskList(tasks);
            break;
        case TODO:
            tasks.addTask(new ToDo(argument));
            ui.showTaskAdded(tasks.getTasks().get(tasks.getSize() - 1), tasks.getSize());
            storage.saveTasks(tasks.getTasks());
            break;
        case DEADLINE:
            String[] deadlineDetails = argument.split("/by");
            if (deadlineDetails.length == 2) {
                tasks.addTask(new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim()));
                ui.showTaskAdded(tasks.getTasks().get(tasks.getSize() - 1), tasks.getSize());
                storage.saveTasks(tasks.getTasks());
            } else {
                throw new DukeException("Invalid deadline format. Please use: deadline <description> /by <date/time>");
            }
            break;
        case EVENT:
            // Handle event command
            String[] eventDetails = argument.split("/from|/to");
            if (eventDetails.length == 3) {
                tasks.addTask(new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim()));
                ui.showTaskAdded(tasks.getTasks().get(tasks.getSize() - 1), tasks.getSize());
                storage.saveTasks(tasks.getTasks());
            } else {
                throw new DukeException("Invalid event format. Please use: event <description> "
                        + "/from <start> /to <end>");
            }
            break;
        case DELETE:
            // Handle delete command
            int taskIndexDelete = Integer.parseInt(argument) - 1;
            tasks.deleteTask(taskIndexDelete, ui);
            storage.saveTasks(tasks.getTasks());
            break;
        case MARK:
            // Handle mark command
            int markIndex = Integer.parseInt(argument) - 1;
            if (isValidIndex(markIndex, tasks.getSize())) {
                tasks.markTaskAsDone(markIndex);
                ui.showTaskMarked(tasks.getTasks().get(markIndex));
                storage.saveTasks(tasks.getTasks());
            } else {
                ui.showInvalidTaskIndex();
            }
            break;
        case UNMARK:
            // Handle unmark command
            int unmarkIndex = Integer.parseInt(argument) - 1;
            if (isValidIndex(unmarkIndex, tasks.getSize())) {
                tasks.unmarkTaskAsDone(unmarkIndex);
                ui.showTaskUnmarked(tasks.getTasks().get(unmarkIndex));
                storage.saveTasks(tasks.getTasks());
            } else {
                ui.showInvalidTaskIndex();
            }
            break;
        case FIND:
            // Handle FIND command
            List<Task> matchingTasks = tasks.findTasksByKeyword(argument);
            ui.showMatchingTasks(matchingTasks);
            break;
        case INVALID:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    /**
     * Checks if the given index is valid for the current task list.
     *
     * @param index The index to be checked.
     * @param size  The size of the task list.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
