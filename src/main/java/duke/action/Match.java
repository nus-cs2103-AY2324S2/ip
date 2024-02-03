package duke.action;

import duke.action.TaskList;

public class Match implements Action {
    private TaskList taskList;

    public Match(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String response() {
        return "";
    }
}
