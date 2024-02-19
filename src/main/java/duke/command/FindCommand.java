package duke.command;

import duke.Storage;
import duke.TaskList;

public class FindCommand extends Command {
    private String input;
    public FindCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Storage storage) {
        String keyword = this.input.trim().substring("find".length()).trim();
        if (keyword.isEmpty()) {
            return "Add keyword to search for";
        } else {
            return tasks.find(keyword);
        }
    }
}
