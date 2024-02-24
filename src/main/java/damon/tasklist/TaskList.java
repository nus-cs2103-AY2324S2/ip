package damon.tasklist;

import damon.task.Task;

import java.util.ArrayList;

/**
 * Stores Tasks by an ArrayList of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList object without parameter.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList object with parameter.
     *
     * @param taskList Arraylist of Tasks that should be encapsulated in this TaskList object.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Returns the size of TaskList, or the number of Tasks in TaskList.
     *
     * @return TaskList size.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns task of specific index in TaskList.
     *
     * @param index Index of the Task to be returned in TaskList
     * @return Required Task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task Task to be added in TaskList.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes Task of specific index in TaskList.
     *
     * @param index Index of Task to be deleted in TaskList.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Marks Task of specific as done status in TaskList.
     *
     * @param index Index of Task to be marked as done status in TaskList.
     */
    public void markTask(int index) {
        this.taskList.get(index).markAsDone();
    }

    /**
     * Un-marks Task of specific to not done status in TaskList.
     *
     * @param index Index of Task to be un-marked to not done status in TaskList.
     */
    public void unMarkTask(int index) {
        this.taskList.get(index).markBackNotDone();
    }

    /**
     * Returns TaskList's ArrayList of Tasks
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    public TaskList findTasks(String keyword) {
        TaskList searchedTaskList = new TaskList();

        for (int i = 0; i < this.size(); i++) {
            Task currentTask = this.get(i);
            if (currentTask.isContainKeyword(keyword)) {
                searchedTaskList.addTask(currentTask);
            }
        }

        return searchedTaskList;
    }
}
