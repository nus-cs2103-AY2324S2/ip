package quacky;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    /*
    This method prints a given task at position i
     */
    public String printTask(int i){
        Task task = tasks.get(i);
        return task.toString();
    }
    public int taskNumber() {
        return tasks.size();
    }
    public void markCompleteTask(int i) throws QuackyException {
        try {
            Task task = tasks.get(i);
            task.markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new QuackyException("Quack. The task is not found");
        }
    }
    public void unmarkCompleteTask (int i){
        Task task = tasks.get(i);
        task.unmarkDone();
    }
    public void deleteTask(int i){
        tasks.remove(i);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size();i++){
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        return sb.toString();
    }

    protected String printSimplified() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toFileString()).append("\n");
        }

        String simplifiedString = sb.toString();
        return simplifiedString;

    }

    /**
     * Finds and returns a TaskList of tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing the tasks that match the keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}