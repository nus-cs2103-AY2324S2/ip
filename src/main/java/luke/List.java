package luke;

import java.io.IOException;
import java.util.ArrayList;

import luke.task.Task;

/**
 * Contains the tasks and manages them with its methods.
 */
public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        assert tasks != null;
        this.tasks = tasks;
    }

    /**
     * Adds a task to the taskList, saves the changes to the savefile and displays it to the user.
     * @param task The task to be added to taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @return String to display in the ui.
     * @throws IOException As the storage read/writes the save file.
     */
    public String addTask(Task task, Storage storage, Ui ui) throws IOException {
        assert task != null;
        assert storage != null;
        assert ui != null;
        tasks.add(task);
        storage.saveTasks(this);
        return ui.displayTaskAdded(task, tasks.size());
    }

    /**
     * Deletes a task from the taskList, saves the changes to the savefile and displays it to the user.
     * @param taskNum The number such that the task with that index is deleted from taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @return String to display in the ui.
     * @throws IOException As the storage read/writes the save file.
     */
    public String deleteTask(int taskNum, Storage storage, Ui ui) throws IOException {
        assert isValidTaskNum(taskNum);
        assert storage != null;
        assert ui != null;
        Task task = tasks.get(taskNum);
        tasks.remove(taskNum);
        storage.saveTasks(this);
        return ui.displayTaskDeleted(task, tasks.size());
    }

    /**
     * Returns whether the task number is valid.
     * @param taskNum The number in the user input (for mark/unmark).
     * @return Whether is task is valid.
     */
    public boolean isValidTaskNum(int taskNum) {
        return taskNum >= 0 && taskNum < tasks.size();
    }

    /**
     * Marks a task in the taskList, saves the changes to the savefile and displays it to the user.
     *
     * @param taskNum The number such that the task with that index is marked in the taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui      The ui that confirms to the user about the action.
     * @return String to display in the ui.
     * @throws IOException As the storage read/writes the save file.
     */
    public String markTask(int taskNum, Storage storage, Ui ui) throws IOException {
        assert isValidTaskNum(taskNum);
        assert storage != null;
        assert ui != null;
        String result = tasks.get(taskNum).mark(ui);
        storage.saveTasks(this);
        return result;
    }

    /**
     * Unmarks a task in the taskList, saves the changes to the savefile and displays it to the user.
     * @param taskNum The number such that the task with that index is unmarked in the taskList.
     * @param storage The storage that saves the changed tasks to save file.
     * @param ui The ui that confirms to the user about the action.
     * @return String to display in the ui.
     * @throws IOException As the storage read/writes the save file.
     */
    public String unmarkTask(int taskNum, Storage storage, Ui ui) throws IOException {
        assert isValidTaskNum(taskNum);
        assert storage != null;
        assert ui != null;
        String result = tasks.get(taskNum).unmark(ui);
        storage.saveTasks(this);
        return result;
    }

    /**
     * Finds tasks that contain the keyword(s).
     * @param keywords The words that the tasks in the resulting list should contain.
     * @param ui The ui that displays the resulting list.
     * @return String to display in the ui.
     */
    public String findTasks(String keywords, Ui ui) {
        assert keywords != null;
        assert ui != null;
        List results = new List(new ArrayList<>());
        for (Task task : tasks) {
            if (task.hasKeywords(keywords)) {
                results.tasks.add(task);
            }
        }
        return ui.displayResults(results);
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
