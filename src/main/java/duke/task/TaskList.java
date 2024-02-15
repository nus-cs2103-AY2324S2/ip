package duke.task;

import duke.response.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskList {
    ArrayList<Task> tasks;
    Ui response;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.response = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    private boolean checkValidTaskIndex(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= this.tasks.size();
    }

    public String addTask(Task newTask){
        tasks.add(newTask);
        return response.addNewTask(newTask, getTaskListSize());
    }

    public void addTaskSilent(Task newTask){
        tasks.add(newTask);
    }

    public String deleteTask(int taskIndex) {
        if (!checkValidTaskIndex(taskIndex)) {
            return response.invalidTaskIndex();
        }
        Task removedTask = this.tasks.remove(taskIndex - 1);
        return response.deleteTask(removedTask, getTaskListSize());
    }

    public String deleteTasks(int[] taskIndices) {
        StringBuilder removedTasks = new StringBuilder();
        ArrayList<Integer> invalidIndexes = new ArrayList<Integer>();
        for (int index : taskIndices) {
            if (!checkValidTaskIndex(index)) {
                invalidIndexes.add(index);
            } else {
                Task removedTask = this.tasks.remove(index - 1);
                removedTasks = removedTasks.append("  ").append(removedTask).append("\n");
            }
        }
        return response.deleteTasks(String.valueOf(removedTasks), invalidIndexes, getTaskListSize());
    }


    public String markTaskAsDone(int taskIndex) {
        if (!checkValidTaskIndex(taskIndex)) {
            return response.invalidTaskIndex();
        }
        Task doneTask = this.tasks.get(taskIndex - 1);
        doneTask.markAsDone();
        return response.markAsDone(doneTask);
    }

    public String markTaskAsUndone(int taskIndex) {
        if (!checkValidTaskIndex(taskIndex)) {
            return response.invalidTaskIndex();
        }
        Task undoneTask = this.tasks.get(taskIndex - 1);
        undoneTask.markAsUndone();
        return response.markAsUndone(undoneTask);
    }

    public String searchTasks(String keyword) {
        // Search for tasks containing the specified keyword
        int currentIndex = 1;
        String tasksList = "";
        for (Task task : this.tasks) {
            String lowerCasedTasks = task.toString().toLowerCase();
            if (lowerCasedTasks.contains(keyword.toLowerCase())) {
                tasksList = tasksList
                        + "\n " + currentIndex + ". " + task.toString();
                currentIndex += 1;
            }
        }
        if (currentIndex == 1) {
            return response.noMatchingTasks(keyword);
        }
        return "Found " + currentIndex + " matching task(s) with '" + keyword + "'"
                + tasksList;
    }
}
