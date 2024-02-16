package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    public String message;

    /**
     * Constructs the class FindCommand.
     *
     * @param message The command line that the user types in.
     */
    public FindCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            StringBuilder string = new StringBuilder();
            String keyWord = message.split(" ", 2)[1].toLowerCase();
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getDescription().toLowerCase().contains(keyWord)) {
                    string.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
                }
            }
            ui.showMatchingTasks(string.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showWrongFormat();
            ui.showFindFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
