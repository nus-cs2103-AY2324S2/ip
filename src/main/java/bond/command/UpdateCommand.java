package bond.command;

import java.util.Arrays;
import java.util.Optional;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.DeadlineTask;
import bond.task.EventTask;
import bond.task.Task;
import bond.task.TaskList;
import bond.task.TodoTask;

/**
 * The UpdateCommand class is used to represent an update command in the Bond task.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class UpdateCommand extends Command {

    private final int index;
    private final String updateInfo;

    /**
     * Constructor for the UpdateCommand class.
     *
     * @param index      The index of the task to be updated.
     * @param updateInfo The updated information of the task.
     */
    public UpdateCommand(int index, String updateInfo) {
        super("update");
        this.index = index;
        this.updateInfo = updateInfo;
    }

    protected String getUpdateInfo() {
        return this.updateInfo;
    }

    protected boolean containDeadlineExclusiveInfo() {
        return this.updateInfo.contains("/d");
    }

    protected boolean containEventExclusiveInfo() {
        return this.updateInfo.contains("/s") || this.updateInfo.contains("/e");
    }

    /**
     * Updates the task list and storage with the modified task.
     * The user input for index is always 1 larger than the referenced
     * location of corresponding task in tasks where the 5th task in the
     * list is located at index 4 in tasks.
     *
     * @param tasks   The list of tasks.
     * @param ui      The responses to user input.
     * @param storage The storage object.
     * @param index   The index number of the task to be updated.
     * @return The response message representing a successful execution of command.
     * @throws BondException If an error occurs during the execution of the command.
     */
    private String update(TaskList tasks, Ui ui, Storage storage, int index)
            throws BondException {
        String response = null;

        Task currentTask = tasks.getTask(index);
        String currentTaskDescription = currentTask.toString();

        if (currentTaskDescription.contains("[T]")) {
            TodoTask task = (TodoTask) currentTask;

            response = new UpdateTodoCommand(this.index, this.updateInfo, task)
                    .execute(tasks, ui, storage);
        } else if (currentTaskDescription.contains("[D]")) {
            DeadlineTask task = (DeadlineTask) currentTask;

            response = new UpdateDeadlineCommand(this.index, this.updateInfo, task)
                    .execute(tasks, ui, storage);
        } else if (currentTaskDescription.contains("[E]")) {
            EventTask task = (EventTask) currentTask;

            response = new UpdateEventCommand(this.index, this.updateInfo, task)
                    .execute(tasks, ui, storage);
        } else {
            assert false : "Task type not recognised";
            BondException.raiseException("update", "LOAD_FAILURE");
        }

        assert response != null : "Response message should not be null";

        return response;
    }

    private boolean isFoundIn(String element, String... elements) {
        for (String e : elements) {
            if (element.equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Extracts the field value from the user input.
     *
     * @param input      The user input.
     * @param tag        The field type to be extracted.
     * @param otherTags  The other tags that can possibly be extracted.
     * @return The field value extracted from the user input given a tag.
     */
    protected String extractFieldValue(String input, String tag, String... otherTags) {
        String[] components = input.split(" ");

        Optional<String> fieldValue = Arrays.stream(components)
                .dropWhile(s -> !s.equals(tag))
                .skip(1)
                .takeWhile(s -> !isFoundIn(s, otherTags))
                .reduce((s1, s2) -> s1 + " " + s2);

        return fieldValue.orElse(null);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        if (this.index < 0 || this.index >= tasks.numberOfTasks()) {
            BondException.raiseException("update", "INVALID_INDEX");
        }

        return update(tasks, ui, storage, this.index);
    }
}
