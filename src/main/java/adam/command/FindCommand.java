package adam.command;

import java.util.ArrayList;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

//CHECKSTYLE.OFF: MissingJavadocType
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        ArrayList<String> matches = taskList.find(keyword);
        String[] results = new String[matches.size() + 1];

        results[0] = "Here are the matching tasks in your list:";
        // Copy the matches into results
        for (int i = 1; i < results.length; i++) {
            results[i] = matches.get(i - 1);
        }

        return ui.showResult(results);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
//CHECKSTYLE.ON: MissingJavadocType
