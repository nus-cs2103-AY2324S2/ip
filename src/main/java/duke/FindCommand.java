package duke;

/**
 * Represents the command to find a task given a keyword.
 */
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list:");
        try {
            TaskList filteredList = taskList.find(this.keyword);
            for (int i = 0; i < filteredList.size(); i++) {
                int index = i + 1;
                ui.showMessage(index + ". " + filteredList.get(0).toString());
            }
        } catch (DukeException e) {
            ui.showMessage("");
        }
    }
}
