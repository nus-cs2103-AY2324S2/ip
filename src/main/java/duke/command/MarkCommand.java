package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final String message;
    public MarkCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String number = message.split(" ")[1];
            int integerNumber = Integer.parseInt(number);
            taskList.mark(integerNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showForgetTaskNumber();
            ui.showMarkFormat();
        } catch (NumberFormatException e) {
            ui.showWrongFormat();
            ui.showMarkFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
