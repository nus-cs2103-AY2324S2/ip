package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class unMarkCommand extends Command {

    private String input;

    public unMarkCommand(String userInput) {
        this.input = userInput;
    }

    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
