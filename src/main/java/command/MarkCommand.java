package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;

/**
 * A MarkCommand to mark the task as completion.
 * A subclass of Command class.
 */
public class MarkCommand extends Command {

    /** The information from the user to mark a Task. */
    private String input;

    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput The information to mark a task.
     */
    public MarkCommand(String userInput) {
        this.input = userInput;
    }


    /**
     * Marks a task as completion.
     * If no task is selected, no task at the moment, or invalid index, an error message is returned.
     * Return a response from Andelu.
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

        int choiceMark;
        try {
            choiceMark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new AndeluException("Please enter a valid integer value.");
        }

        if (choiceMark <= tasks.getTasks().size() && choiceMark > 0) {
            tasks.markTask(choiceMark - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Nice! I've marked this task as done:\n");
            stringBuilder.append(tasks.getTasks().get(choiceMark - 1).toString() + "\n");

            return stringBuilder.toString();

        } else {
            throw new AndeluException("Invalid choice.");
        }
    }
}
