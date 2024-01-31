package blu.task;

import java.util.ArrayList;
import java.util.List;

import blu.exception.IllegalParameterException;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    private boolean isValidIndex(int idx) {
        if (idx < 0 || idx >= tasks.size()) {
            return false;
        }
        return true;
    }

    public Task getTask(int taskNumber) throws IllegalParameterException {
        int idx = taskNumber - 1;
        if (!isValidIndex(idx)) {
            throw new IllegalParameterException("Could not retrieve task number " 
                + taskNumber + "\n"
                + "Please use the list command to view valid task numbers.");
        }
        return this.tasks.get(idx);
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int taskNumber) throws IllegalParameterException {
        int idx = taskNumber - 1;
        if (!isValidIndex(idx)) {
            throw new IllegalParameterException("Could not delete task number " 
            + taskNumber + "\n"
            + "Please use the list command to view valid task numbers.");
        }
        this.tasks.remove(idx);
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "All tasks completed!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            String title = String.format("%d. %s", i + 1, task.toString());
            // do not add new line when for last task in list
            if (i == this.tasks.size() - 1) {
                stringBuilder.append(title); 
            } else {
                stringBuilder.append(title + "\n");
            }
        }
        return stringBuilder.toString();
    }
}
