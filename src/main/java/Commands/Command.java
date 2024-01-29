package Commands;

import Storage.Storage;
import TaskList.TaskList;
import TaskList.Tasks.Task;

import java.io.IOException;
import java.util.List;

public abstract class Command {

    protected TaskList taskList;

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    abstract public String execute() throws IOException;
}
