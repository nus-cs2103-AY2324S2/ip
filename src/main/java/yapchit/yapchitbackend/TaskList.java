package yapchit.yapchitbackend;

import yapchit.yapchitbackend.tasks.Task;
import yapchit.yapchitexceptions.InvalidDetailException;

import java.util.ArrayList;

/**
 * Encapsulates an arraylist of tasks and performs necessary operations to manipulate it.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initates new TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Deletes item from list.
     *
     * @param idx index of item to delete.
     */
    public void delete(int idx) {
        this.tasks.remove(idx);
    }

    /**
     * Marks item as completed or incomplete.
     *
     * @param idx item to be marked.
     * @param isDone boolean.
     * @throws InvalidDetailException if the mark detail is invalid.
     */
    public void mark(int idx, boolean isDone) throws InvalidDetailException {

        if (idx >= tasks.size()) {
            throw new InvalidDetailException("Invalid item index, please try again.");
        } else {
            tasks.get(idx).setDone(isDone);
        }
    }

    /**
     * Adds task to list.
     *
     * @param t task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Returns size of list.
     *
     * @return int: the size of list.
     */
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Returns item at specified index in list.
     *
     * @param i index of task to locate.
     * @return Task that is at the specified index.
     */
    public Task getItem(int i) {
        return this.tasks.get(i);
    }


    /**
     * Finds list of tasks that have a specific search term.
     *
     * @param term search term.
     * @return TaskList with names that have search term.
     */
    public TaskList findSublist(String term) {
        TaskList sublist = new TaskList();

        for (Task task : tasks) {
            if(task.getName().contains(term)){
                sublist.addTask(task);
            }
        }
        return sublist;
    }
}
