package snomtasklist;

import java.util.ArrayList;

import snomexceptions.InvalidCommandIndexException;
import snomtask.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    private int counter;


    private TaskList() {
        this.taskList = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Makes a empty tasklist.
     * @return a empty tasklist.
     */
    public static TaskList makeTaskList() {
        return new TaskList();
    }

    /**
     * Gets the tasklist at the specified index within the tasklist.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @return the task at the specified index.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     */
    public Task getTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.taskList.get(pos);
        }

    }

    /**
     * Marks the task at the specified index within the tasklist as done.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     * @return a String representing whether the task is successfully added.
     */
    public String markTask(int pos) throws InvalidCommandIndexException {


        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;

            this.taskList.get(pos).doTask();
            return "Succesfully marked task";

        }

    }


    /**
     * Unmarks the task at the specified index within the tasklist as undone.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     * @return a string representing if the task is successfully unmarked.
     */

    public String unmarkTask(int pos) throws InvalidCommandIndexException {


        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.taskList.get(pos).undoTask();
            return "Succesfully unmarked task";
        }
    }




    /**
     * deletes the task at the specified index within the tasklist.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     * @return a string representing if the task is successfully deleted.
     */
    public String deleteTask(int pos) throws InvalidCommandIndexException {


        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.taskList.remove(pos);
            return "Succesfully removed task";

        }

    }


    /**
     * Adds a new task to the tasklist.
     * @return a string representing if the task is
     *           successfully added to the tasklist.
     */
    public String addTask(Task t) {
        this.counter += 1;
        assert t != null : "Invald task added";
        this.taskList.add(t);
        return "Succesfully added task";

    }

    /**
     * Gets the number of tasks in the tasklist.
     * @return an integer representing the number
     *         of the tasks within the tasklist.
     */
    public int getCounter() {
        return this.counter;
    }


    /**
     * Prints out all the tasks in the tasklist.
     * @return a string representing if the task is
     *         successfully added to the tasklist.
     */
    public String displayTaskList() {
        StringBuilder lst = new StringBuilder();
        for (int i = 0; i<counter; i++) {
            lst.append(this.taskList.get(i) + "\n");

        }
        return lst.toString();
    }


    /**
     * Prints out the tasks matching the description within
     *         the tasklist.
     * @param cmd is the command containing the word that
     *            the user wishes to search for in the tasklist.
     */
    public String printMatchingTasks(String cmd) {
        ArrayList<Task> found_tasks = new ArrayList<>();
        for (int i = 0; i<counter; i++) {
            if (this.taskList.get(i).match(cmd)) {
                found_tasks.add(taskList.get(i));
            }
        }
        StringBuilder lst = new StringBuilder();
        if (found_tasks.size() > 0) {
            System.out.println("Here are the tasks that match your description");
            for (int j = 0; j < found_tasks.size(); j++) {
                lst.append(this.taskList.get(j) + "\n");
            }
            return lst.toString();
        } else {
            return "Sorry, we did not find any tasks that matched your description";
        }
    }
}
