package commands;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).getDescription().contains(keyword)) {
                output.append((i+1) + ". " + taskList.getTask(i).toString() + "\n");
            }
        }
        return output.toString();
    }
}
