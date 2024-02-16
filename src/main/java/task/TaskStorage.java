package task;

import java.util.ArrayList;

import util.Messages;

/**
 * This Object stores all Task objects created in the main interface.
 * Uses the ArrayList data structure to store the Task, and allows for manipulation,
 * such as addition, deletion, and editing Tasks.
 */
public class TaskStorage {
    private ArrayList<Task> storageList;

    /**
     * Constructs an empty ArrayList to store Task objects.
     */
    public TaskStorage() {
        this.storageList = new ArrayList<>();
    }

    /**
     * Constructs TaskStorage object with an existing ArrayList.
     *
     * @param taskList the pre-existing ArrayList to be saved.
     */
    public TaskStorage(ArrayList<Task> taskList) {
        if (taskList != null) {
            this.storageList = taskList;
        } else {
            this.storageList = new ArrayList<>();
        }
    }

    /**
     * Adds an Task object to the end of the ArrayList.
     *
     * @param task The Task object to be added to the ArrayList.
     */
    public void addTask(Task task) {
        assert task != null : "Not instantiated object should not be added";
        this.storageList.add(task);
    }

    /**
     * Removes an Task object from the ArrayList.
     *
     * @param id The id number of element to be removed from the ArrayList.
     */
    public String removeTask(int id) {
        assert id <= this.storageList.size() : "The id should not be more than the storage size!";
        Task t;
        try {
            t = this.storageList.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            return Messages.MESSAGE_NO_SUCH_ELEMENTS;
        }
        this.storageList.remove(id - 1);
        return t.toString();
    }

    /**
     * Edits the specified Task to change its completedness.
     *
     * @param id The id number of element to be edited.
     * @param mark To mark as complete or to unmark.
     */
    public String markTask(int id, boolean mark) {
        assert id <= this.storageList.size() : "The id should not be more than the storage size!";
        Task t;
        try {
            t = this.storageList.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            return Messages.MESSAGE_NO_SUCH_ELEMENTS;
        }
        if (mark) {
            t.mark();
        } else {
            t.unmark();
        }
        return t.toString();
    }

    /**
     * Searches the existing stored Task for the keyword.
     * Uses a loop to go through each element and check it's String.
     *
     * @param keyword Keyword that the we are looking for.
     */
    public String searchForTask(String keyword) {
        assert keyword.length() != 0 : "A keyword should be included during a search!";
        ArrayList<Task> keywordHits = new ArrayList<>();
        ArrayList<Integer> keywordHitIndexes = new ArrayList<>();
        int counter = 1;
        for (Task task:this.storageList) {
            if (task.toString().contains(keyword)) {
                keywordHits.add(task);
                keywordHitIndexes.add(counter);
            }
            counter++;
        }

        String totalAnswer = "";
        for (int i = 0; i < keywordHits.size(); i++) {
            totalAnswer += (keywordHitIndexes.get(i)) + "." + keywordHits.get(i);
            if (i != keywordHits.size() - 1) {
                totalAnswer += "\n";
            }
        }
        return totalAnswer;
    }

    /**
     * Returns the size of the ArrayList.
     */
    public int size() {
        return this.storageList.size();
    }

    /**
     * To get the ArrayList stored for printing purposes.
     */
    public ArrayList<Task> getStorage() {
        return this.storageList;
    }

    /**
     * Returns a string representation of this TaskStorage.
     * This includes all the Task name, and conditions of each task.
     *
     * @return a string representation of this TaskStorage.
     */
    @Override
    public String toString() {
        String totalAns = "";
        for (int i = 0; i < this.storageList.size(); i++) {
            totalAns += (i + 1) + "." + this.storageList.get(i);
            if (i != this.storageList.size() - 1) {
                totalAns += "\n";
            }
        }
        return totalAns;
    }
}
