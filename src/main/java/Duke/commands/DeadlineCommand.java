package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidDeadlineException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;



/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String[] words;
    /**
     * Constructs a DeadlineCommand with the specified array of words.
     * @param words The array of words representing the deadline command.
     */

    public DeadlineCommand(String[] words) {
        super();
        this.words = words;
    }
    /**
     * Executes the DeadlineCommand to add a deadline task.
     * @param tasks The TaskList containing the tasks.
     * @param ui The UI object for user interaction.
     * @param storage The Storage object for data storage.
     * @return False, indicating that the command should not exit the application.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("deadline");
        }
        int deadlineStartIdx = words[1].indexOf("/by");
        if (deadlineStartIdx == -1) {
            throw new InvalidDeadlineException();
        }
        Task newTask = new Deadline(words[1].substring(0, deadlineStartIdx),
                words[1].substring(deadlineStartIdx + 4));
        ui.displayAdd(tasks.addTask(newTask), tasks.getItems().size());
        storage.addToWriteFile(newTask);
        return false;
    }
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("deadline");
        }
        int deadlineStartIdx = words[1].indexOf("/by");
        if (deadlineStartIdx == -1) {
            throw new InvalidDeadlineException();
        }
        Task newTask = new Deadline(words[1].substring(0, deadlineStartIdx),
                words[1].substring(deadlineStartIdx + 4));
        storage.addToWriteFile(newTask);
        return ui.addTaskMessage(tasks.addTask(newTask), tasks.getItems().size());
    }
}
