package Duke;

import java.util.ArrayList;
import Duke.command.*;
import Duke.task.*;

/**
 * A system to store a list of task with additional functionality
 * such as inserting, deleting, marking, unmarkinng, etc.
 */
public class TaskList{
    private ArrayList<task> strlist = new ArrayList<>();
    private int number_task = 0;

    /**
     * Construct TaskList using an ArrayList of tasks
     *
     * @param tasklist ArrayList of tasks
     */
    TaskList(ArrayList<task> tasklist){
        this.strlist = tasklist;
        number_task = this.strlist.size();
    }

    /**
     * Accessor to access the number of tasks stored in TaskList
     *
     * @return the number of tasks stored in the TaskList
     */
    public int accessNumberTask(){
        return number_task;
    }

    /**
     * Access the ArrayList of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<task> accessList(){
        return strlist;
    }

    /**
     * Inserting a task into the TaskList
     *
     * @param tsk the task to be added
     */
    public void insert(task tsk){
        strlist.add(tsk);
        number_task++;
    }

    /**
     * delete a task from the TaskList with specified index
     *
     * @param index the index of task to be deleted
     * @return the string representation of the deleted task
     */
    public String delete(int index){
        String temp = strlist.get(index-1).toString();
        strlist.remove(index-1);
        number_task--;
        return temp;
    }

    /**
     * mark the task with specified index as done
     *
     * @param index the index of tasks to be marked as done
     * @return the string representation fo the marked task
     */
    public String mark(int index){
        strlist.get(index-1).mark();
        return strlist.get(index-1).toString();
    }

    /**
     * unmark the task with specified index as not done
     *
     * @param index  the index of task to be resumed as not done
     * @return the string representation of the unmarked task
     */
    public String unmark(int index){
        strlist.get(index-1).unmark();
        return strlist.get(index-1).toString();
    }

    /**
     * Returns the string representation of the task list
     *
     * @return a string listing all the tasks with each prefixed with its index number
     */
    @Override
    public String toString(){
        String str = "";
        for (int i=0; i<strlist.size();i++){
            if (i == strlist.size()-1){
                str = str + (i+1)+". "+ strlist.get(i);
                break;
            }
            str = str + (i+1)+". "+ strlist.get(i)+ "\n ";
        }
        return str;
    }
}
