package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final String message;
    public DeleteCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String number = message.split(" ")[1];
            int n = Integer.parseInt(number);
            taskList.delete(n);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showForgetTaskNumber();
            ui.showDeleteFormat();
        } catch (NumberFormatException e) {
            ui.showWrongFormat();
            ui.showDeleteFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
