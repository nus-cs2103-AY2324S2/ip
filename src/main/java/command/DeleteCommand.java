package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * A DeleteCommand class to delete tasks.
 * A subclass of Command class.
 */
public class DeleteCommand extends Command {

    /** The information from the user to delete a Task. */
    private String input;

    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput
     */
    public DeleteCommand(String userInput) {
        this.input = userInput;
    }

    /**
     * Deletes a task based on the index.
     * If there is no task selected or the list of tasks is empty, then an error message is returned.
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If the list of tasks is empty, no task selected, or invalid index.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (tasks.getTasks().size() == 0) {
            throw new DukeException("No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("Please select the task.");
        }

        int choiceDelete;
        try {
            choiceDelete = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer value.");
        }

        if (choiceDelete <= tasks.getTasks().size() && choiceDelete > 0) {
            Task deletedTask = tasks.getIndividualTask(choiceDelete - 1);
            tasks.removeTask(choiceDelete - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);
            ui.printAnyStatement("Noted, I've removed this task:");
            ui.printAnyStatement(deletedTask.toString());
            ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        } else {
            throw new DukeException("Invalid choice");
        }
    }
}
