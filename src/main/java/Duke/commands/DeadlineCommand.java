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

    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean isOneWord = words.length == 1;
        if (isOneWord) {
            throw new EmptyDescriptionException("deadline");
        }

        int deadlineStartIdx = words[1].indexOf("/by");
        boolean isInvalidDeadline = deadlineStartIdx == -1;
        if (isInvalidDeadline) {
            throw new InvalidDeadlineException();
        }

        String deadlineDescription = words[1].substring(0, deadlineStartIdx);
        String deadlineDate = words[1].substring(deadlineStartIdx + 4);
        Task newDeadline = new Deadline(deadlineDescription, deadlineDate);
        assert newDeadline != null;
        tasks.addTask(newDeadline);
        storage.addToWriteFile(newDeadline);
        int numberOfCurrentTasks = tasks.getNumberOfTasks();
        String result = ui.addTaskMessage(newDeadline, numberOfCurrentTasks);
        return result;
    }
}
