package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A UnmarkCommand to mark the task as not complete.
 * A subclass of Command class.
 */
public class UnmarkCommand extends Command {

    /** The information from the user to unmark a Task. */
    private String input;


    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput
     */
    public UnmarkCommand(String userInput) {
        this.input = userInput;
    }


    /**
     * Unmarks a task as not complete.
     * If no task is selected, no task at the moment, or invalid index, an error message is returned.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If no task is selected, no task at the moment, or invalid index.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String splitInput[] = input.split(" ");
        if (tasks.getTasks().size() == 0) {
            throw new DukeException("No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("Please select the task.");
        }

        int choiceUnmark;
        try {
            choiceUnmark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer value.");
        }

        if (choiceUnmark <= tasks.getTasks().size() && choiceUnmark > 0) {
            tasks.unmarkTask(choiceUnmark - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);
            ui.printAnyStatement("OK, I've marked this task as not done yet:");
            ui.printAnyStatement(tasks.getTasks().get(choiceUnmark - 1).toString());
        } else {
            throw new DukeException("Invalid choice.");
        }
    }
}
