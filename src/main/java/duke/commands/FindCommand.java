package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;
    private List<String> messages = new ArrayList<>();

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public List<String> execute(TaskList tasks) throws DukeException {
        messages.add("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(keyword)) {
                messages.add(tasks.get(i).toString());
            }
        }
        return messages;
    }
}
