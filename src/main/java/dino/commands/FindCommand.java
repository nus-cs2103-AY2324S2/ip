package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;
    private List<String> messages = new ArrayList<>();

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public List<String> execute(TaskList tasks) throws DinoException {
        int order = 1;
        messages.add("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                messages.add(order + "." + tasks.get(i).toString());
                order++;
            }
        }
        return messages;
    }
}
