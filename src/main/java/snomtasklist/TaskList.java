package snomtasklist;

import java.util.ArrayList;

import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomexceptions.InvalidCommandTaskDoneException;
import snomexceptions.InvalidCommandTaskNotDoneException;
import snomtask.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    private int counter;


    private TaskList() {
        this.taskList = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Makes an empty taskList.
     * @return an empty taskList.
     */
    public static TaskList makeTaskList() {
        return new TaskList();
    }

    /**
     * Gets the TaskList at the specified index within the TaskList.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @return the task at the specified index.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the TaskList.
     */
    public Task getTaskAtIndex(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.taskList.get(pos);
        }

    }

    /**
     * Marks the task at the specified index within the TaskList as done.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the TaskList.
     * @return a String representing whether the task is successfully added.
     */
    public String markTaskAtIndex(int pos) throws InvalidCommandException {
        try {
            Task t = getTaskAtIndex(pos);
            t.doTask();
            return "Successfully marked task";
        } catch (InvalidCommandIndexException e) {
            throw e;
        } catch (InvalidCommandTaskDoneException e) {
            throw e;
        }
    }


    /**
     * Unmarks the task at the specified index within the TaskList as undone.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the TaskList.
     * @return a string representing if the task is successfully unmarked.
     */

    public String unmarkTaskAtIndex(int pos) throws InvalidCommandException {
        try {
            Task t = getTaskAtIndex(pos);
            t.undoTask();
            return "Successfully unmarked task";
        } catch (InvalidCommandIndexException e) {
            throw e;
        } catch (InvalidCommandTaskNotDoneException e) {
            throw e;
        }
    }




    /**
     * deletes the task at the specified index within the TaskList.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the TaskList.
     * @return a string representing if the task is successfully deleted.
     */
    public String deleteTaskAtIndex(int pos) throws InvalidCommandIndexException {


        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.taskList.remove(pos);
            return "Successfully removed task";

        }

    }


    /**
     * Adds a new task to the TaskList.
     * @return a string representing if the task is
     *           successfully added to the TaskList.
     */
    public String addTask(Task t) {
        this.counter += 1;
        assert t != null : "Invalid task added";
        this.taskList.add(t);
        return "Successfully added task";

    }

    /**
     * Gets the number of tasks in the TaskList.
     * @return an integer representing the number
     *         of the tasks within the TaskList.
     */
    public int getTaskNum() {
        return this.counter;
    }


    /**
     * Prints out all the tasks in the TaskList.
     * @return a string representing if the task is
     *         successfully added to the TaskList.
     */
    public String getTasks() {
        StringBuilder lst = new StringBuilder();
        for (int i = 0; i<counter; i++) {
            lst.append(this.taskList.get(i) + "\n");

        }
        return lst.toString();
    }


    /**
     * Prints out the tasks matching the description within
     *         the TaskList.
     * @param cmd is the command containing the word that
     *            the user wishes to search for in the TaskList.
     */
    public String getMatchingTasks(String cmd) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i<counter; i++) {
            if (this.taskList.get(i).isMatch(cmd)) {
                foundTasks.add(taskList.get(i));
            }
        }
        StringBuilder lst = new StringBuilder();
        if (!foundTasks.isEmpty()) {
            System.out.println("Here are the tasks that match your description");
            for (int j = 0; j < foundTasks.size(); j++) {
                lst.append(this.taskList.get(j) + "\n");
            }
            return lst.toString();
        } else {
            return "Sorry, we did not find any tasks that matched your description";
        }
    }

    public boolean checkDuplicateTask(Task t) {
        return this.taskList.stream().anyMatch(x -> x.equals(t));
    }

    public boolean isEmpty() {
        return this.counter == 0;
    }
}
