package command;

import tasklist.BarebonesTaskList;
import tasklist.TaskList;

public class FindCmd extends Command {
    private String query;
    private BarebonesTaskList result;
    @Override
    public void execute() {
        // return another taskList
        result = tasks.find(query);
        ui.printTasks(result);
    }

    public BarebonesTaskList getResult() {
        return result;
    }

    public FindCmd(String query) {
        this.query = query;
    }
}
