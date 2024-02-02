package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;
import jade.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JadeException {
        StringBuilder sb = new StringBuilder();
        int count = 0; // track the number of matching tasks found
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containsKeyword(keyword)) {
                count++;
                sb.append(String.format("\t%d. %s\n", count, tasks.get(i)));
            }
        }
        ui.printMessage(String.format("\tHere are the matching tasks in your list:\n%s", sb));
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
