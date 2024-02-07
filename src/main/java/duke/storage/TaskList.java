package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) throws DukeException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new DukeException("OOPS!!! Invalid index provided.");
        }
        return this.taskList.get(index);
    }

    public Task remove(int index) {
        Task target = this.taskList.get(index);
        this.taskList.remove(index);

        return target;
    }

    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            taskListString.append(i + 1).append(".").append(taskList.get(i));

            if (i < taskList.size() - 1) {
                taskListString.append("\n");
            }
        }

        return taskListString.toString();
    }
}
