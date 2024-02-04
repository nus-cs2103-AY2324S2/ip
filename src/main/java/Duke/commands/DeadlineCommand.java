package Duke.commands;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.exceptions.DukeException;
import Duke.exceptions.EmptyDescriptionException;
import Duke.exceptions.InvalidDeadlineException;
import Duke.tasks.Task;
import Duke.tasks.Deadline;
public class DeadlineCommand extends Commands {
    private String[] words;
    public DeadlineCommand(String[] words) {
        super();
        this.words = words;
    }
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
        ui.displayAdd(tasks.addTask(newTask), tasks.list().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
