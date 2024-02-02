package command;

import exceptions.DukeException;
import task.Task;
import task.TaskList;
import utilities.Storage;
import utilities.Ui;

/**
 * Controls what to do when user marks task.
 */
public class MarkCommand extends Command {
    /**
     * The task index of which task is to be marked.
     */
    private int taskIndex;
    /**
     * The mark status of a given task.
     */
    private boolean isMarked;

    /**
     * MarkCommand class constructor.
     * @param userInput The input that the user types into the command line.
     * @param isMarked The variable that determines whether a given task is to be marked or unmarked.
     */
    public MarkCommand(String userInput, boolean isMarked) {
        super(false);
        this.taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        this.isMarked = isMarked;
    }

    /**
     * Executes the mark task process.
     * @param taskList The task list that the command is applied to.
     * @param storage The storage that the task list is stored in after the given task is marked.
     * @param ui Provides corresponding user output based on whether the process is successful or not.
     * @throws DukeException The exception thrown when an invalid put is entered.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task taskToMark = taskList.markTask(this.taskIndex, this.isMarked);
            ui.showMarkTask(taskToMark, this.isMarked);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid input. Please provide a valid task index or check that the task exists.");
        }
        storage.save(taskList);
    }
}
