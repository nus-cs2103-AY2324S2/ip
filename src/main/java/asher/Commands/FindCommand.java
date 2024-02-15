package asher.Commands;

import asher.Ui.Ui;
import asher.Tasks.TaskList;
import asher.Tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        String keyword = input.substring(5).trim();
        ArrayList<Task> matchingTasks = taskList.searchKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}