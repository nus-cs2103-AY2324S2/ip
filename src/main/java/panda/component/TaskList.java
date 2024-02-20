package panda.component;

import java.util.ArrayList;

import panda.command.Command;
import panda.task.Task;

import panda.exception.PandaException;

public class TaskList {
    private ArrayList<Task> tlist;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        tlist = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with tasks loaded from a list of commands.
     * 
     * @param clist the list of commands to load tasks from.
     * @throws PandaException if an error occurs during task loading.
     */
    public TaskList(ArrayList<Command> clist) throws PandaException {
        tlist = new ArrayList<>();
        for(Command c : clist) {
            c.execute(this);
        }
    }

    /**
     * Inserts a task into the TaskList.
     * 
     * @param task the task to insert.
     */
    public void insert(Task task) {
        tlist.add(task);
    }

    /**
     * Removes a task from the TaskList based on its index.
     * 
     * @param idx the index of the task to remove.
     */
    public void remove(int idx) {
        tlist.remove(tlist.get(idx - 1));
    }

    /**
     * Marks a task in the TaskList based on its index.
     * 
     * @param idx the index of the task to mark.
     */
    public void mark(int idx) {
        tlist.get(idx - 1).mark();
    }

    /**
     * Unmarks a task in the TaskList based on its index.
     * 
     * @param idx the index of the task to unmark.
     */
    public void unmark(int idx) {
        tlist.get(idx - 1).unmark();
    }

    /**
     * Tags a task in the TaskList based on its index.
     * 
     * @param idx the index of the task to mark.
     */
    public void tag(int idx, String tag) {
        tlist.get(idx - 1).tag(tag);
    }

    /**
     * Untags a task in the TaskList based on its index.
     * 
     * @param idx the index of the task to unmark.
     */
    public void untag(int idx, String tag) {
        tlist.get(idx - 1).untag(tag);
    }

    /**
     * Returns the number of tasks in the TaskList.
     * 
     * @return the number of tasks in the TaskList.
     */
    public int size() {
        return tlist.size();
    }

    /**
     * Returns the string representation of a task in the TaskList based on its index.
     * 
     * @param idx the index of the task to return.
     * @return the string representation of the task.
     */
    public String getTaskString(int idx) {
        return tlist.get(idx - 1).toString();
    }

    /**
     * Searches for tasks that match the given filter string.
     * The filter string is treated as a regular expression and the comparison is case insensitive.
     * 
     * @param fString the filter string to match against.
     * @return a new TaskList containing only the tasks that match the filter string.
     */
    public TaskList find(String fString) {
        TaskList tmp = new TaskList();
        for(Task task : tlist) {
            if(task.match(fString)) {
                tmp.insert(task);
            }
        }
        return tmp;
    }

    /**
     * Searches for tasks that match the given filter tag.
     * Tag has to be an exact match.
     * 
     * @param fString the filter string to match against.
     * @return a new TaskList containing only the tasks that match the filter string.
     */
    public TaskList filter(String tag) {
        TaskList tmp = new TaskList();
        for(Task task : tlist) {
            if(task.isTagged(tag)) {
                tmp.insert(task);
            }
        }
        return tmp;
    }

    /**
     * Returns a string representation of the TaskList.
     * If the TaskList is empty, returns a message indicating that the list is empty.
     * Otherwise, returns a string listing all the tasks in the TaskList.
     * 
     * @return a string representation of the TaskList.
     */
    public String toString() {
        if(tlist.size() == 0) {
            return "Your list is empty.";
        }

        String result = "Here are the tasks in your list:";
        for(int i = 1; i <= tlist.size(); i++) {
            result = result + "\n" + ((i) + "." + getTaskString(i));
        }
        return result;
    }

    /**
     * Returns the string representation of the TaskList suitable for saving to a file.
     * 
     * @return the string representation of the TaskList.
     */
    public String toSaveString() {
        String result = "";
        for(Task t : tlist) {
            result = result + t.toSaveString() + "\n";
        }
        return result;
    }
}
