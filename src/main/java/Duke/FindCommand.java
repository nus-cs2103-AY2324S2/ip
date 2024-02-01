package Duke;

public class FindCommand implements Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTasksByKeyword(keyword, tasks.getTasks());

    }
}

