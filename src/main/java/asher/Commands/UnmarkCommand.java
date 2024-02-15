package asher.Commands;

import asher.BotException;
import asher.Tasks.TaskList;
import asher.Ui.Ui;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            int taskNumber = taskList.getTaskNumber(input);
            if (taskNumber != -1) {
                taskList.getTasks().get(taskNumber).markUndone();
                return ui.showUnmarkTaskMessage(taskList.getTasks().get(taskNumber));
            } else {
                throw new BotException("Invalid Task!");
            }
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
