package teletubbi;

/**
 * Represents the command to find a task given a keyword.
 */
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String result = "Here are the matching tasks in your list:\n";
        try {
            TaskList filteredList = taskList.find(this.keyword);
            for (int i = 0; i < filteredList.size(); i++) {
                int index = i + 1;
                result = result + index + ". " + filteredList.get(i).toString() + "\n";
            }
        } catch (TeletubbiException e) {
            result = "";
        }
        return result;
    }
}
