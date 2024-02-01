package duke;

import java.util.ArrayList;

/**
 * Represent the class that contains the task list
 * e.g., it has operations to add/delete tasks in the list
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class TaskList {

    private ArrayList<Task> tasks;


    /**
     * Constructor for a TaskList instance
     *
     * @param  inputTaskArray an ArrayList<Task> to load in
     */
    public TaskList(ArrayList<Task> inputTaskArray){
        tasks = inputTaskArray;
    }

    /**
     * Constructor for a TaskList instance given no previous records
     *
     */
    public TaskList(){

        tasks = new ArrayList<Task>();
    }

    public int getSize(){
        return tasks.size();
    }

    /**
     * Prints the Task array to user, directs user to input Tasks if empty
     */
    public void showTasks() throws DukeException{

        if(tasks.size() == 0){
            throw new DukeException("Add tasks to list first! Type something other than List/list or Bye/bye.\n");
        } else {
            System.out.println("Here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i+1 + "." + tasks.get(i).toString());
            }
            System.out.println();
        }
    }


    /**
     * Marks a Task in the Task Arraylist as requested by the user
     *
     * @param num index of Task in taskList to mark
     */
    public void markMechanism(int num) throws DukeException {
        if(num <= tasks.size() && num > 0){
            System.out.println(tasks.get(num-1).markAsDone());
        } else {
            throw new DukeException("Please mark a valid task!\n");
        }
    }


    /**
     * Unmarks a Task in the Task Arraylist as requested by the user
     *
     * @param num index of Task in taskList to unmark
     */
    public void unmarkMechanism(int num) throws DukeException{
        if(num <= tasks.size() && num > 0){
            System.out.println(tasks.get(num-1).unMarkTask());
        } else {
            throw new DukeException("Please mark a valid task!\n");
        }
    }

    /**
     * Adds a Task in the Task Arraylist as requested by the user
     *
     * @param taskToAdd Task to be added into taskList
     */
    public void taskMechanism(Task taskToAdd) throws DukeException {
        tasks.add(taskToAdd);

        System.out.println("Understood. I've added this task:\n "
                + tasks.size() + "."
                + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size()
                + " task(s) in the list.\n");

    }



    /**
     * Deletes a Task in the Task Arraylist as requested by the user
     *
     * @param num index of Task in taskList to be removed
     * @return Task that was deleted for storage to settle
     */
    public Task deleteMechanism(int num) {
        Task removed = new Task("Task to be deleted");
        try {
            removed = tasks.remove(num - 1);
            System.out.println("Very well. I have removed this task.\n" + removed
                    + "\nNow you have " + tasks.size()
                    + " task(s) in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are only: " + tasks.size() + " task(s) in the list to delete.\n");
        }
        return removed;
    }
}
