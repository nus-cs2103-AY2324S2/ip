package duke.task;//package duke;

import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= this.tasks.size();
    }

    public String addTask(Task newTask){
        tasks.add(newTask);
        return ui.addNewTask(newTask, getTaskListSize());
    }

    public void addTaskSilent(Task newTask){
        tasks.add(newTask);
    }

    public String deleteTask(int taskIndex) {
        assert(taskIndex >= 1);
        if (!isValidTaskIndex(taskIndex)) {
            return ui.invalidTaskIndex();
        }
        Task removedTask = this.tasks.remove(taskIndex - 1);
        return ui.deleteTask(removedTask, getTaskListSize());
    }
    public String markTaskAsDone(int taskIndex) {
        assert(taskIndex >= 1);
        if (!isValidTaskIndex(taskIndex)) {
            return ui.invalidTaskIndex();
        }
        Task doneTask = this.tasks.get(taskIndex - 1);
        doneTask.markAsDone();
        return ui.markAsDone(doneTask);
    }

    public String markTaskAsUndone(int taskIndex) {
        assert(taskIndex >= 1);
        if (!isValidTaskIndex(taskIndex)) {
            return ui.invalidTaskIndex();
        }
        Task undoneTask = this.tasks.get(taskIndex - 1);
        undoneTask.markAsUndone();
        return ui.markAsUndone(undoneTask);
    }

    public String searchTasks(String keyword) {
        // Search for tasks containing the specified keyword
        int currentIndex = 1;
        boolean isFound = false;
        String tasksList = "";
        for (Task task : this.tasks) {
            String lowerCasedTasks = task.toString().toLowerCase();
            if (lowerCasedTasks.contains(keyword.toLowerCase())) {
                tasksList = tasksList + currentIndex + ". " + task.toString() + "\n";
                currentIndex += 1;
                isFound = true;
            }
        }
        if (!isFound) {
            return this.ui.noMatchingTasks(keyword);
        }
        return tasksList;
    }
}

