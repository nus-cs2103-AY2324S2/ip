package kitchensink;

import java.io.IOException;
import java.util.ArrayList;

import kitchensink.task.Task;

/**
 * Contains the tasks and manages them with its methods.
 */
public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the taskList, saves the changes to the savefile and displays it to the user.
     * @param task The task to be added to taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @throws IOException As the storage read/writes the save file.
     */
    public void addTask(Task task, Storage storage, Ui ui) throws IOException {
        tasks.add(task);
        storage.saveTasks(this);
        ui.sayTaskAdded(task, tasks.size());
    }

    /**
     * Deletes a task from the taskList, saves the changes to the savefile and displays it to the user.
     * @param taskNum The number such that the task with that index is deleted from taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @throws IOException As the storage read/writes the save file.
     */
    public void deleteTask(int taskNum, Storage storage, Ui ui) throws IOException {
        Task task = tasks.get(taskNum);
        tasks.remove(taskNum);
        storage.saveTasks(this);
        ui.sayTaskDeleted(task, tasks.size());
    }

    /**
     * Returns whether the task number is valid.
     * @param taskNum The number in the user input (for mark/unmark).
     * @return Whether is task is valid.
     */
    public boolean validTaskNum(int taskNum) {
        return taskNum > 0 && taskNum <= tasks.size();
    }

    /**
     * Marks a task in the taskList, saves the changes to the savefile and displays it to the user.
     * @param taskNum The number such that the task with that index is marked in the taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @throws IOException As the storage read/writes the save file.
     */
    public void markTask(int taskNum, Storage storage, Ui ui) throws IOException {
        tasks.get(taskNum).mark(ui);
        storage.saveTasks(this);
    }

    /**
     * Unmarks a task in the taskList, saves the changes to the savefile and displays it to the user.
     * @param taskNum The number such that the task with that index is unmarked in the taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @throws IOException As the storage read/writes the save file.
     */
    public void unmarkTask(int taskNum, Storage storage, Ui ui) throws IOException {
        tasks.get(taskNum).unmark(ui);
        storage.saveTasks(this);
    }

    public int getListSize() {
        return tasks.size();
    }

    /**
     * Returns a String displaying the tasks in the taskList.
     * Format is as shown in Level-8.
     * @return String representation of taskList.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                result.append("\n");
            }
        }
        return String.valueOf(result);
    }
}
