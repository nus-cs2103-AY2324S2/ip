package yapchit;

import yapchit.tasks.Task;
import yapchit.yapchitexceptions.InvalidDetailException;

import java.util.ArrayList;

/**
 * Encapsulates an arraylist of tasks and performs necessary operations to manipulate it.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Initates new TaskList instance.
     */
    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * deletes item from list
     *
     * @param idx index of item to delete
     */
    public void delete(int idx){
        this.list.remove(idx);
    }

    /**
     * marks item as completed or incomplete
     *
     * @param idx item to be marked
     * @param val boolean
     * @throws InvalidDetailException
     */
    public void mark(int idx, boolean val) throws InvalidDetailException{
        if(idx >= list.size()){
            throw new InvalidDetailException("Invalid item index, please try again");
        } else {
            list.get(idx).updateTag(val);
        }
    }

    public TaskList findSublist(String term){
        TaskList sublist = new TaskList();

        for(Task task : list){
            if(task.getName().contains(term)){
                sublist.addTask(task);
            }
        }
        return sublist;
    }

    /**
     * adds task to list.
     * @param t task to add.
     */
    public void addTask(Task t){
        list.add(t);
    }

    /**
     * Returns size of list
     *
     * @return int: the size of list
     */
    public int getListSize(){
        return this.list.size();
    }

    /**
     * Returns item at specified index in list.
     *
     * @param i index of task to locate
     * @return Task that is at the specified index
     */
    public Task getItem(int i){
        return this.list.get(i);
    }
}
