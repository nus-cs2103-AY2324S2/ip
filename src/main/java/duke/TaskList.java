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
     * @param  inputTaskArray an ArrayList of Tasks to load in
     */
    public TaskList(ArrayList<Task> inputTaskArray) {
        tasks = inputTaskArray;
    }

    /**
     * Constructor for a TaskList instance given no previous records
     *
     */
    public TaskList() {

        tasks = new ArrayList<Task>();
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints the taskList to user, directs user to input Tasks if empty
     *
     * @return string representation of the taskList
     * @throws DukeException when taskList is empty
     */
    public String showTasks() throws DukeException {
        StringBuilder sb = new StringBuilder();

        if (tasks.size() == 0) {
            throw new DukeException("Add tasks to list first! Type something other than List/list or Bye/bye.\n");
        } else {
            //System.out.println("Here are the tasks in your list:\n");
            sb.append("Here are the tasks in your list:\n\n");

            for (int i = 0; i < tasks.size(); i++) {
                //System.out.println(i + 1 + "." + tasks.get(i).toString());
                sb.append(i + 1 + "." + tasks.get(i).toString() + "\n");
            }

            //System.out.println();
            sb.append("\n");
        }
        return sb.toString();
    }


    /**
     * Marks a Task in the Task Arraylist as requested by the user
     *
     * @param num index of Task in taskList to mark
     * @throws DukeException when invalid Task number is given
     */
    public String markMechanism(int num) throws DukeException {
        if (num <= tasks.size() && num > 0) {
            return tasks.get(num - 1).markAsDone();
        } else {
            throw new DukeException("Please mark a valid task!\n");
        }
    }


    /**
     * Unmarks a Task in the Task Arraylist as requested by the user
     *
     * @param num index of Task in taskList to unmark
     * @throws DukeException when invalid Task number is given
     */
    public String unmarkMechanism(int num) throws DukeException {
        if (num <= tasks.size() && num > 0) {
            return tasks.get(num - 1).unmarkTask();
        } else {
            throw new DukeException("Please mark a valid task!\n");
        }
    }

    /**
     * Adds a Task in the Task Arraylist as requested by the user
     *
     * @param taskToAdd Task to be added into taskList
     */
    public String taskMechanism(Task taskToAdd) {
        tasks.add(taskToAdd);

        return "Understood. I've added this task:\n "
                + tasks.size() + "."
                + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size()
                + " task(s) in the list.\n";

    }

    /**
     * Finds Tasks with keyword in the taskList as requested by the user
     *
     * @param keyword word that Task should contain
     * @return an ArrayList of Tasks of the matching Tasks
     * @throws DukeException when current taskList is empty
     */
    public String findMechanism(String keyword) throws DukeException {
        ArrayList<Task> searchResults = new ArrayList<Task>();
        if (tasks.size() == 0) {
            throw new DukeException("Add tasks to list first before finding tasks\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    searchResults.add(tasks.get(i));
                }
            }

            if (searchResults.size() == 0) {
                return "Unfortunately, there are no keywords matching your search.\n";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks matching your keyword:\n");
            for (int i = 0; i < searchResults.size(); i++) {
                sb.append(i + 1 + "." + searchResults.get(i).toString() + "\n");
                //System.out.println();
            }
            return sb.toString();
        }
    }

    /**
     * Deletes a Task in the Task Arraylist as requested by the user
     *
     * @param num index of Task in taskList to be removed
     * @return Task that was deleted for storage to settle
     * @throws IndexOutOfBoundsException when user gives an invalid value
     */
    public String deleteMechanism(int num) throws IndexOutOfBoundsException {
        Task removed = tasks.remove(num - 1);

        return "Very well. I have removed this task.\n"
                + removed
                + "\nNow you have " + tasks.size()
                + " task(s) in the list.\n";
    }
}
