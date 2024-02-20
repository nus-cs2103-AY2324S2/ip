package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;
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
     * Returns the response from Andelu.
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException If the list of tasks is empty, no task selected, or invalid index.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        assert input != null : "input should not be null";
        String[] splitInput = input.split(" ");
        if (tasks.getTasks().size() == 0) {
            throw new AndeluException("No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new AndeluException("Please select the task.");
        }

        int choiceDelete;
        try {
            choiceDelete = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new AndeluException("Please enter a valid integer value.");
        }

        if (choiceDelete <= tasks.getTasks().size() && choiceDelete > 0) {
            Task deletedTask = tasks.getIndividualTask(choiceDelete - 1);
            tasks.removeTask(choiceDelete - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Noted, I've removed this task:\n");
            stringBuilder.append(deletedTask.toString() + "\n");
            stringBuilder.append("Now you have " + tasks.getTasks().size() + " tasks in the list.\n");
            ui.printAnyStatement("Noted, I've removed this task:");
            ui.printAnyStatement(deletedTask.toString());
            ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
            return stringBuilder.toString();

        } else {
            throw new AndeluException("Invalid choice");
        }
    }
}
