package riz.data;

import java.util.ArrayList;

/**
 * A class that stores all the current Tasks in a List and provides some
 * additional useful functions such as find().
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Assigns a given ArrayList into the taskList field.
     * @param taskList the ArrayList of tasks in which we want to store in our TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves the task at index i.
     * @param i the index of the task we want to retrieve
     * @return the task at index i of the task list
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Adds a task to the back of the list.
     * @param task the Task object which we want to append to the TaskList.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task at index i of the TaskList
     * @param i the index of the task to be removed.
     */
    public void remove(int i) {
        this.taskList.remove(i);
    }

    /**
     * The number of Tasks currently in the TaskList
     * @return The number of Tasks currently in the TaskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves the ArrayList in which all the Tasks are stored in.
     * @return the ArrayList in which all the Task objects are stored in
     * in the current TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Empties out the TaskList of all it's tasks.
     */
    public void clearList() {
        this.taskList.clear();
    }

    /**
     * Given a word, prints out all the tasks whose description contains the word.
     * @param word The input which we want to search if there are any matching tasks.
     */
    public String find(String word) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks matching " + "\"").append(word).append("\"...\n");
        int count = 1;
        for (Task task : this.taskList) {
            if (task.find(word)) {
                sb.append(count).append( ". ");
                sb.append(task.toString()).append("\n");
                count++;
            }
        }
        sb.append("\n\n");
        return sb.toString();
    }

}