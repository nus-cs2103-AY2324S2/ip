package command;

import exceptions.DukeException;
import task.Task;
import task.TaskList;
import utilities.Storage;

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
     * @return The response expected from the chatbot.
     * @throws DukeException The exception thrown when an invalid put is entered.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            Task taskToMark = taskList.markTask(this.taskIndex, this.isMarked);
            storage.save(taskList);
            String marked = String.format("done:\n%s\n%s", taskToMark.toString(), taskList.numTaskToString());
            String unmarked = String.format("not done yet:\n%s\n%s", taskToMark.toString(), taskList.numTaskToString());
            String s = "Nice! I've marked this task as " + (this.isMarked ? marked : unmarked);
            return s;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid input. Please provide a valid task index or check that the task exists.");
        }
    }
}
