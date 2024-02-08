package command;

import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;

/**
 * Command to find an event in the task list.
 */
public class FindCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public FindCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }
    @Override

    public void execute(TaskList taskList, Ui ui) throws EmptyInputException {
        String input = ui.getInput();
        if (input.length() > 4) {
            String keyword = input.substring(5);
            taskList.find(keyword);
        } else {
            throw new EmptyInputException("find");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
