package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A MarkCommand to mark the task as completion.
 * A subclass of Command class.
 */
public class MarkCommand extends Command {

    private String input;

    /**
     * Creates a constructor with userInput as argument.
     * @param userInput
     */
    public MarkCommand(String userInput) {
        this.input = userInput;
    }


    /**
     * Marks a task as completion.
     * If no task is selected, no task at the moment, or invalid index, an error message is returned.
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If no task is selected, no task at the moment, or invalid index.
     */
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String splitInput[] = input.split(" ");
        if (tasks.getTasks().size() == 0) {
            throw new DukeException("No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("Please select the task.");
        }
        int choiceMark;
        try {
            choiceMark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer value.");
        }
        if (choiceMark <= tasks.getTasks().size() && choiceMark > 0) {
            tasks.markTask(choiceMark - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);
            ui.printAnyStatement("Nice! I've marked this task as done:");
            ui.printAnyStatement(tasks.getTasks().get(choiceMark - 1).toString());
        } else {
            throw new DukeException("Invalid choice.");
        }
    }
}
