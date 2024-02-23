package commands;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class RemindCommand extends Command {
    private String input;

    public RemindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int indexOfOrder = input.indexOf(" ") + 1;
        int indexOfDate = input.lastIndexOf(" ") + 1;
        int order = Integer.parseInt(input.substring(indexOfOrder, indexOfDate - 1)) - 1;
        String date = input.substring(indexOfDate);
        tasks.setReminder(order, date);
        return ui.showSetReminderStatus();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
