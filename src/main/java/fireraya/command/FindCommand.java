package fireraya.command;

import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String key) {
        this.keyword = key;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filtered = tasks.filter(keyword);
        String a = ui.print("Here are some results I found in your list!\n");
        String b = ui.listTasks(filtered);
        return  a + b;
    }
}
