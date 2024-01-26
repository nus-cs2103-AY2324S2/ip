/**
 * This class represents a task list of up to 100 Tasks
 *
 * @author Billy Ho Cheng En
 */

import java.util.ArrayList;
import java.lang.StringBuilder;

class TaskList{

    private ArrayList<Task> arr;

    /**
     * Constructor for a task list.
     */
    public TaskList(){
        arr = new ArrayList<Task>(100);
    }

    /**
     * Adds a task to the back of the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task){
        this.arr.add(task);
    }

    /**
     * Marks the task at the given index as done
     *
     * @param index the index of the task to be marked done
     * @return true if task was already done, else false
     */
    public boolean mark_done(int index){
        return arr.get(index).mark_done();
    }

    /**
     * Marks the task at the given index as not done
     *
     * @param index the index of the task to be marked not done
     * @return true if task was already not done, else false
     */
    public boolean unmark_done(int index){
        return arr.get(index).unmark_done();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.size(); i++) {
            sb.append((i + 1) + ". " + arr.get(i) + "\n");
        }
        return sb.toString();
    }
}