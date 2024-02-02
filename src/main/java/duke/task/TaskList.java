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

    public void addTask(Task newTask){
        tasks.add(newTask);
        ui.addNewTask(newTask, getTaskListSize());
    }

    public void addTaskSilent(Task newTask){
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        if (!isValidTaskIndex(taskIndex)) {
            ui.invalidTaskIndex();
        }
        Task removedTask = this.tasks.remove(taskIndex - 1);
        ui.deleteTask(removedTask, getTaskListSize());
    }
    public void markTaskAsDone(int taskIndex) {
        if (!isValidTaskIndex(taskIndex)) {
            ui.invalidTaskIndex();
        }
        Task doneTask = this.tasks.get(taskIndex - 1);
        doneTask.markAsDone();
        ui.markAsDone(doneTask);
    }

    public void markTaskAsUndone(int taskIndex) {
        if (!isValidTaskIndex(taskIndex)) {
            ui.invalidTaskIndex();
        }
        Task undoneTask = this.tasks.get(taskIndex - 1);
        undoneTask.markAsUndone();
        ui.markAsUndone(undoneTask);
    }

    public void searchTasks(String keyword) {
        // Search for tasks containing the specified keyword
        int currentIndex = 1;
        boolean isFound = false;
        for (Task task : this.tasks) {
            String lowerCasedTasks = task.toString().toLowerCase();
            if (lowerCasedTasks.contains(keyword.toLowerCase())) {
                System.out.println(currentIndex + ". " + task.toString());
                currentIndex += 1;
                isFound = true;
            }
        }
        if (!isFound) {
            this.ui.noMatchingTasks(keyword);
        }
    }
}

