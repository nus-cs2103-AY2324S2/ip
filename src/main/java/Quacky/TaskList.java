package Quacky;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * Manages a list of tasks in the Quacky application. This class provides functionalities
 * to add, remove, mark, and unmark tasks, as well as to represent the tasks in string form.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Returns a string representation of the task at the specified position in the task list.
     *
     * @param i The index of the task in the list.
     * @return The string representation of the task.
     */
    public String printTask(int i){
        Task task = tasks.get(i);
        return task.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int taskNumber() {
        return tasks.size();
    }

    /**
     * Marks the task at the specified position as completed.
     *
     * @param i The index of the task in the list.
     * @throws QuackyException if the index is out of bounds.
     */
    public void markCompleteTask(int i) throws QuackyException {
        try {
            Task task = tasks.get(i);
            task.markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new QuackyException("Quack. The task is not found");
        }
    }
    /**
     * Marks the task at the specified position as not completed.
     *
     * @param i The index of the task in the list.
     * @throws QuackyException if the index is out of bounds
     */
    public void unmarkCompleteTask(int i) throws QuackyException {
        try {
            Task task = tasks.get(i);
            task.unmarkDone();
        } catch (IndexOutOfBoundsException e) {
            throw new QuackyException("Quack. The task is not found");
        }
    }
    /**
     * Removes the task at the specified position from the task list.
     *
     * @param i The index of the task in the list.
     * @throws QuackyException if the index is out of bounds
     */
    public void deleteTask(int i) throws QuackyException{
        try {
            tasks.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new QuackyException("Quack. The task is not found");
        }
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

    /**
     * Generates a simplified string representation of all tasks, suitable for file storage.
     *
     * @return A parsable string representation of all tasks in the list.
     */
    protected String printSimplified() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toFileString()).append("\n");
        }

        String simplifiedString = sb.toString();
        return simplifiedString;

    }

}
