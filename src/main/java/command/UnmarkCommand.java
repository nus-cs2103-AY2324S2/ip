package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;

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
     * Returns a response from Andelu.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException If no task is selected, no task at the moment, or invalid index.
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

        int choiceUnmark;
        try {
            choiceUnmark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new AndeluException("Please enter a valid integer value.");
        }

        if (choiceUnmark <= tasks.getTasks().size() && choiceUnmark > 0) {
            tasks.unmarkTask(choiceUnmark - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);
            ui.printAnyStatement("OK, I've marked this task as not done yet:");
            ui.printAnyStatement(tasks.getTasks().get(choiceUnmark - 1).toString());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OK, I've marked this task as not done yet:\n");
            stringBuilder.append(tasks.getTasks().get(choiceUnmark - 1).toString() + "\n");
            return stringBuilder.toString();
        } else {
            throw new AndeluException("Invalid choice.");
        }
    }
}
