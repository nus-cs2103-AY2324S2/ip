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
}

