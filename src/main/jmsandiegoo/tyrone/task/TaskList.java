package jmsandiegoo.tyrone.task;

import jmsandiegoo.tyrone.common.DateTime;
import jmsandiegoo.tyrone.exceptions.StorageHelperException;
import jmsandiegoo.tyrone.storage.StorageHelper;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the task list of the application.
 */
public class TaskList {
    private ArrayList<Task> items;

    private static final StorageHelper storageHelper = new StorageHelper();

    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Returns the current number of items in the list.
     *
     * @return int
     */
    public int getListSize() {
        return this.items.size();
    }

    /**
     * Returns the item from the list with the target index.
     *
     * @param index - target list index of the item to get.
     * @return Task - the task item.
     */
    public Task getItem(int index) {
        return this.items.get(index);
    }

    /**
     * Loads the task list from the storage to the application.
     * Throws StorageHelperException if there is an IO or parsing error.
     *
     * @throws StorageHelperException - if io or parsing error occurred.
     */
    public void loadTaskListFromFile() throws StorageHelperException {
        try {
            File file = storageHelper.loadFile();

            // read the file and parse into the array
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] strArr = sc.nextLine().split(" \\| ");
                Task t = deserializeTaskStr(strArr);
                this.items.add(t);
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
            throw new StorageHelperException("Error loading the local task list file.");
        }
    }

    private Task deserializeTaskStr(String[] strArr) throws IOException {
        Task t;
        switch (strArr[0]) {
        case "T":
            t = new ToDo(strArr[2]);
            break;
        case "D":
            t = new Deadline(strArr[2], new DateTime(strArr[3]));
            break;
        case "E":
            String[] periodArr = strArr[3].split(" - ");
            t = new Event(strArr[2], new DateTime(periodArr[0]), new DateTime(periodArr[1]));
            break;
        default:
            throw new IOException("Invalid text file format.");
        }

        if (strArr[1].equals("1")) {
            t.markItem();
        }

        return t;
    }

    /**
     * Saves the task list from the application to the storage.
     * Throws StorageHelperException if there is an IO or parsing error.
     *
     * @throws StorageHelperException - if io or parsing error occurred.
     */
    public void saveTaskListToFile() throws StorageHelperException {
        StringBuilder content = new StringBuilder();
        for (Task item : this.items) {
            content.append(item.serializeTask()).append("\n");
        }

        try {
            storageHelper.saveFile(content.toString());
        } catch (IOException | SecurityException e) {
            throw new StorageHelperException("Failed to save the list changes locally. My bad...");
        }
    }

    /**
     * Adds the item specified into the task list.
     *
     * @param item - the new task to add into list.
     */
    public void addItem(Task item) {
        this.items.add(item);
    }

    /**
     * Marks the item as done.
     *
     * @param index - the target list index of the item to be marked.
     */
    public void markItemDone(int index) {
        if (index < 0 || index >= this.getListSize()) {
            return;
        }

        Task currItem = this.items.get(index);
        currItem.markItem();
    }

    /**
     * Un-marks the item as done.
     *
     * @param index - the target list index of the item to be un-marked.
     */
    public void unmarkItemDone(int index) {
        if (index < 0 || index >= this.getListSize()) {
            return;
        }

        Task currItem = this.items.get(index);
        currItem.unmarkItem();
    }

    /**
     * Returns the deleted item from the task list specified
     * by a target index.
     *
     * @param index - the target list index of the item to be deleted.
     * @return Task - deleted task.
     */
    public Task deleteItem(int index) {
        if (index < 0 || index >= this.getListSize()) {
            return null;
        }

        return this.items.remove(index);
    }

    /**
     * Returns a new copy of the task list.
     *
     * @return TaskList.
     */
    public TaskList copy() {
        TaskList taskListCopy = new TaskList();
        for (Task item : this.items) {
            taskListCopy.addItem(item.copy());
        }

        return taskListCopy;
    }

    /**
     * Restores the current task list to the state provided as the
     * parameter.
     *
     * @param restoreTaskList - the state to restore to.
     */
    public void restoreFromGivenList(TaskList restoreTaskList) {
        this.items.clear();

        for (int i = 0; i < restoreTaskList.getListSize(); ++i) {
            this.items.add(restoreTaskList.getItem(i));
        }
    }

    /**
     * Returns the task items from the list with the keyword.
     *
     * @param keyword - the target keyword to search for.
     * @return TaskList - the list of tasks with the keyword.
     */
    public TaskList findItemsByKeyword(String keyword) {
        TaskList findTaskList = new TaskList();
        for (Task item : this.items) {
            if (!item.hasKeyword(keyword)) {
                continue;
            }

            findTaskList.addItem(item);
        }

        return findTaskList;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.items.size(); ++i) {
            output.append(i + 1).append(".  ").append(this.items.get(i).toString());

            if (i < this.items.size() - 1) {
                output.append("\n");
            }
        }

        return output.toString();
    }
}
