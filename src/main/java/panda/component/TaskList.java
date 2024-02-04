package panda.component;
import java.util.ArrayList;

import panda.command.Command;
import panda.exception.PandaException;
import panda.task.Task;

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
    public String taskString(int idx) {
        return tlist.get(idx).toString();
    }

    /**
     * Returns the string representation of the TaskList.
     * 
     * @return the string representation of the TaskList.
     */
    public String toString() {
        if(tlist.size() == 0) {
            return "Your list is empty.";
        }

        String result = "Here are the tasks in your list:";
        for(int i = 0; i < tlist.size(); i++) {
            result = result + "\n" + ((i + 1) + "." + taskString(i));
        }
        return result;
    }

    /**
     * Returns the string representation of the TaskList suitable for saving to a file.
     * 
     * @return the string representation of the TaskList.
     */
    public String saveString() {
        String result = "";
        for(Task t : tlist) {
            result = result + t.saveString() + "\n";
        }
        return result;
    }
}
