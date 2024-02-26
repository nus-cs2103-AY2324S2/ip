package duke;

import duke.task.Task;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Task list.
 * Used for maitaining list of tasks in Duke task maintenance application.
 */
public class TaskList {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     * Invokes TaskParser to parse the strings into appropriate Task objects
     *
     * @param tasks the list of tasks in string format
     * @throws DukeException when TaskParser cannot parse the task details
     */
    public TaskList(List<String> tasks) throws DukeException{
        taskList.clear();
        for (String taskStr: tasks) {
            Task task = TaskParser.parse(taskStr);
            taskList.add(task);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Get list of tasks in ArrayList format.
     *
     * @return the ArrayList<Tasks>
     */
    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    /**
     * Get item from list task.
     *
     * @param task Task to locate from TaskList
     * @return the task
     */
    public Task getItemFromList(Task task){
        if (taskList.contains(task)) {
            int index = taskList.indexOf(task);
            return taskList.get(index);
        }
        return null;
    }

    /**
     * Get count by type of task.
     *
     * @param taskType the task type like "E", "D", "T"
     * @return the count of the task objects of given type in TaskList
     */
    public int getCountByType(String taskType){
        int count = 0;
        count = (int) taskList.stream().filter( obj -> ((Task)obj).getTypeOfTask().equals(taskType)).count();
        return count;
    }

    /**
     * Get item from list by index in the TaskList.
     *
     * @param idx the index
     * @return the task object
     */
    public Task getItemFromListByIndex(int idx){
        if (idx >= 0 && idx < taskList.size()) {
            Task task = taskList.get(idx);
            if (null != task) {
                int index = taskList.indexOf(task);
                return taskList.get(index);
            }
            return null;
        }
        return null;
    }

    /**
     * Add Task object to TaskList.
     *
     * @param task Task to TaskList
     */
    public void add(Task task) {
        taskList.add(task);
        pcs.firePropertyChange("ADD", null, Collections.unmodifiableList(taskList));
    }

    /**
     * Update task in TaskList.
     *
     * @param task Task object
     * @return true or false based on whether update is successful or not
     */
    public boolean updateTaskInList(Task task) {
        if (taskList.contains(task)) {
            int index = taskList.indexOf(task);
            taskList.set(index, task);
            pcs.firePropertyChange("UPDATE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }

    /**
     * Update task in TaskList at given index.
     *
     * @param index the index of item in TaskList
     * @param task     the Task object
     * @return true or false based on success of update
     */
    public boolean updateTaskInList(int index, Task task) {
        if (taskList.contains(task)) {
            taskList.set(index, task);
            pcs.firePropertyChange("UPDATE", null, taskList);
            System.out.println("tasklist:update:task: "+task.getStringRepresentation());
            return true;
        }
        return false;
    }

    /**
     * Remove item at given index from TaskList.
     *
     * @param index the index of item in TaskList
     * @return true or false indicating removal result
     */
    public boolean removeItemAtIndex(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task obj = taskList.get(index);
            taskList.remove(obj);
            pcs.firePropertyChange("REMOVE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }

    /**
     * Remove Task from TaskList.
     *
     * @param task Task object to be removed from TaskList
     * @return true or false indicating removal result
     */
    public boolean remove(Task task) {
        if (taskList.contains(task)) {
            int index = taskList.indexOf(task);
            Task obj = taskList.get(index);
            taskList.remove(task);
            pcs.firePropertyChange("REMOVE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }

    /**
     * Formats task object details for interactive output to user.
     *
     * @return the string
     */
    public String printOutput() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            sb.append(count).append(".");
            sb.append(task.printOutput());
            if (count < taskList.size()) {
                sb.append("\n");
            }
            count++;
        }
        sb.lastIndexOf("\n");
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            sb.append(task.getStringRepresentation());
            if (count < taskList.size()) {
                sb.append("\n");
            }
            count++;
        }
        return sb.toString();
    }

}
