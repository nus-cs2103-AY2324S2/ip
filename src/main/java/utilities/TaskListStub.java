package utilities;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * Contains the task list e.g. it has operations to add/delete tasks in the list (it simulates these
 * operations for the ParserTest)
 */
public class TaskListStub extends TaskList {
    private List<Task> tasks;

    public TaskListStub() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String deleteFromList(String input) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        if (actualId >= 0 && actualId < tasks.size()) {
            tasks.remove(actualId);
        }
        return null;
    }

    @Override
    public boolean markAndUnmark(String input, boolean isMark) {
        boolean status = true;
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        if (actualId >= 0 && actualId < tasks.size()) {
            Task task = tasks.get(actualId);
            status = task.toggleMarkStatus(isMark);
        }
        return status;
    }

    @Override
    public List<Task> getTasks() {
        return this.tasks;
    }
}
