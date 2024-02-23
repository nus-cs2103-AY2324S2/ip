package Mitsuki;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Contains the task list, as well as operations to edit the list.
 *
 * @author Tee Chu Jie
 */
public class TaskList {
    private static ArrayList<Task> list;
    public ArrayList<Task> getList() {
        return list;
    }
    public TaskList(ArrayList<Task> list) {
        TaskList.list = list;
    }
    public void add(Task task) {
        list.add(task);
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }
    private static Ui ui = new Ui();


    /**
     * Adds a deadline item to the user's list.
     * Uses '/' to separate the deadline from the task.
     * E.g. 'deadline Return book /By Sunday 2pm' adds the task
     *      [D][ ] Return book (By Sunday 2pm)
     *      to the user's list
     * Throws an exception if empty task description or no deadline is given.
     *
     * @param fullString the string with the details of the deadline to add.
     */
    public static String deadline(String fullString) {
        String[] tokens = fullString.split("/");

        try {
            EmptyTaskException.timedValidate(tokens);
        } catch(EmptyTaskException ex) {
            return "Please give a description for your deadline item. Try again!";
        }

        int endOfDesc = fullString.indexOf("/by");
        if (endOfDesc == -1) {
            return "Please give a deadline for your deadline item. Try again! XD"
                    + "E.g. Type 'deadline return book /by 20/12/2024' to add the task 'return book' "
                    + "\nwith a deadline of '20 Dec 2024' to your list.";
        }
        String description = fullString.substring(1, endOfDesc - 1);
        String deadline = fullString.substring(endOfDesc + 4);
        String[] parts = {description, deadline};
        try {
            MissingDeadlineException.validate(parts);
        } catch (MissingDeadlineException ex) {
            return "Please give a deadline for your deadline item. Try again! XD"
                    + "E.g. Type 'deadline return book /by 20/12/2024' to add the task 'return book' "
                    + "\nwith a deadline of '20 Dec 2024' to your list.";
        }

        Task task = new Deadline(description, deadline);
        list.add(task);
        return "OK, I have added the task '" + description + "' to your list! :)"
                + "Now you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Adds a todo item to the user's list.
     * E.g. 'todo Go running' adds the task
     *      [T][ ] Go running
     *      to the user's list.
     * Throws an exception if empty task description is given.
     *
     * @param description1 the String with the details of the todo to add.
     */
    public static String todo(String description1) {

        try {
            EmptyTaskException.validate(description1);
        } catch(EmptyTaskException ex) {
            return "Please give a description for your todo item. Try again!";

        }

        String trimmed1 = description1.trim();
        Task task1 = new Todo(trimmed1);
        list.add(task1);
        return "OK, I have added the task '" + trimmed1 + "' to your list! :)\n"
                + "Now you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Adds an event item to the user's list.
     * Uses '/' to take in the timing of the event.
     * E.g. 'event Party /From Friday 8pm /to 11pm' adds the task
     *      [E][ ] Party (From Friday 8pm to 11pm)
     *      to the user's list.
     * Throws an exception if no task description, or no event start or end timing is given.
     *
     * @param fullString1 the String with the details of the event to add.
     */
    public static String event(String fullString1) {
        String[] tokens1 = fullString1.split("/");

        try {
            EmptyTaskException.timedValidate(tokens1);
            MissingEventTimingException.validate(tokens1);
        } catch(EmptyTaskException ex) {
            return "Please give a description for your event item. Try again!";
        } catch(MissingEventTimingException ex) {
            return "Please give a time period for your event item. Try again! XD\n"
            + "E.g. Type 'event meeting /From Monday 10am /to 12pm' to add the task 'meeting' \n"
                    + "with a time period 'From Monday 10am to 12pm' to your list.";
        }

        String description2 = tokens1[0];
        String trimmed2 = description2.trim();
        String from = tokens1[1];
        String to = tokens1[2];
        String toTrimmed = to.trim();
        Task task2 = new Event(trimmed2, from, toTrimmed);
        list.add(task2);
        return "OK, I have added the task '" + trimmed2 + "' to your list! :)\n"
        + "Now you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Displays the user's current todo list.
     */
    public static String list() {
        String message = "Here are the items in your list:\n";
        int i = 0;
        int j = 1;
        while (i < list.size()) {
            message = message + j + ". " + list.get(i).toString() + "\n";
            i++;
            j++;
        }
        return message;
    }

    /**
     * Marks an item on the user's todo list as done.
     * E.g. 'mark 3' marks the 3rd item on the list as done.
     * Informs user that the task is marked as done, and also displays the task.
     * If task was already marked as done, exception is thrown and user is informed that they
     * had already completed the task.
     *
     * @param index the scanner used to scan the index of the task to mark.
     */
    public static String mark(int index) {
        Task theTask = list.get(index - 1);

        try {
            AlreadyDoneException.validate(theTask);
        } catch(AlreadyDoneException ex) {
            return "You have already completed this task! :D" + "\n" + theTask;
        }

        theTask.markAsDone();
        return "Ok, I have marked this task as done. :D\n" + theTask;
    }

    /**
     * Marks an item on the user's todo list as not done.
     * E.g. 'unmark 3' marks the 3rd item on the list as not done.
     * Informs user that the task is marked as not done, and also displays the task.
     * If task was already marked as not done, exception is thrown and user is informed that they
     * had not yet completed the task.
     *
     * @param index1 the index of the task to unmark.
     */
    public static String unmark(int index1) {
        Task aTask = list.get(index1 - 1);

        try {
            WasNotDoneException.validate(aTask);
        } catch(WasNotDoneException ex) {
            return "You had not completed this task! :O\n" + aTask;
        }

        aTask.markAsNotDone();
        return "Ok, I have marked this task as not done yet. :O\n" + aTask;
    }

    /**
     * Deletes an item from the user's todo list.
     * E.g. 'delete 3' deletes the 3rd item on the list.
     * Informs user that the task is deleted, and also displays the deleted task.
     *
     * @param index2 the index of the task to delete
     */
    public static String delete(int index2) {
        Task goneTask = list.get(index2 - 1);
        list.remove(goneTask);

        return "Alright, I have removed the following task from your list:\n" + goneTask
                + "\nNow you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Finds related items and their index on the user's current list.
     *
     * @param keyWord the keyWord to search for.
     */
    public static String find(String keyWord) {
        return ui.printFound(keyWord, list);
    }
}
