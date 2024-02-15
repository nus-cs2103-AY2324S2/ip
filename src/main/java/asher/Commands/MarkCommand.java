package asher.Commands;

import asher.BotException;
import asher.Ui.Ui;
import asher.Tasks.TaskList;

public class MarkCommand extends Command {

    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            int taskNumber = taskList.getTaskNumber(input);
            if (taskNumber != -1) {
                taskList.getTasks().get(taskNumber).markDone();
                return ui.showMarkTaskMessage(taskList.getTasks().get(taskNumber));
            } else {
                throw new BotException("Invalid Task!");
            }
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
