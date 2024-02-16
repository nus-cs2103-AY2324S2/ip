package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ToDoCommand extends Command {
    public String message;
    public ToDoCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            taskList.createToDo(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showWrongFormat();
            ui.showToDoFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
