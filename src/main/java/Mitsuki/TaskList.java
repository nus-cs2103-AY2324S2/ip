package Mitsuki;

import java.util.ArrayList;


/**
 * Contains the task list, as well as operations to edit the list.
 *
 * @author Tee Chu Jie
 */
public class TaskList {
    private static ArrayList<Task> list;

    /**
     * Constructs an object of the TaskList class.
     *
     * @param list the list of tasks, given as an ArrayList<Task> object.
     */
    public TaskList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    /**
     * Gives the size of the list in the TaskList object.
     *
     * @return an int representing the length of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Gives the task in the list at the stated index.
     *
     * @param index the index of the task to be returned.
     * @return the Task object at the stated index on the list.
     */
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
     * @return a string representing the deadline item.
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
     * @param description the String with the details of the todo to add.
     * @return a String representing the todo item.
     */
    public static String todo(String description) {

        try {
            EmptyTaskException.validate(description);
        } catch(EmptyTaskException ex) {
            return "Please give a description for your todo item. Try again!";

        }

        String trimmed = description.trim();
        Task task = new Todo(trimmed);
        list.add(task);
        return "OK, I have added the task '" + trimmed + "' to your list! :)\n"
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
     * @param fullString the String with the details of the event to add.
     * @return a String representing the event.
     */
    public static String event(String fullString) {
        String[] tokens = fullString.split("/");

        try {
            EmptyTaskException.timedValidate(tokens);
            MissingEventTimingException.validate(tokens);
        } catch (EmptyTaskException ex) {
            return "Please give a description for your event item. Try again!";
        } catch (MissingEventTimingException ex) {
            return "Please give a time period for your event item. Try again! XD\n"
            + "E.g. Type 'event meeting /From Monday 10am /to 12pm' to add the task 'meeting' \n"
                    + "with a time period 'From Monday 10am to 12pm' to your list.";
        }

        String description = tokens[0];
        String trimmed = description.trim();
        String from = tokens[1];
        String to = tokens[2];
        String toTrimmed = to.trim();
        Task task = new Event(trimmed, from, toTrimmed);
        list.add(task);
        return "OK, I have added the task '" + trimmed + "' to your list! :)\n"
        + "Now you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Adds a fixed duration task item to the user's list.
     *      * Uses '/' to take in the duration of the task.
     *      * E.g. 'fixed Party /5 hours' adds the task
     *      *      [F][ ] Party (Needs 5 hours)
     *      *      to the user's list.
     *      * Throws an exception if no task description, or no duration is given.
     *
     * @param fullString the String with the details of the task to add.
     * @return a String representing the task.
     */
    public static String fixedDurationTask(String fullString) {
        String[] tokens = fullString.split("/");

        try {
            EmptyTaskException.timedValidate(tokens);
            MissingDurationException.validate(tokens);
        } catch (EmptyTaskException ex) {
            return "Please give a description for your fixed duration task. Try again!";
        } catch (MissingDurationException ex) {
            return "Please give a duration for your fixed duration task. Try again! XD\n"
                    + "E.g. type 'fixed read book /2 hours' to add the task 'read book' \n"
                    + "with a fixed duration of '2 hours' to your list.";
        }
        String description = tokens[0];
        String trimmed = description.trim();
        String duration = tokens[1].trim();
        Task task = new FixedDurationTask(trimmed, duration);
        list.add(task);
        return "OK, I have added the task '" + trimmed + "' to your list! :)\n"
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
     * @return a String representing the marked task.
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
     * @param index the index of the task to unmark.
     * @return a String representing the unmarked task.
     */
    public static String unmark(int index) {
        Task aTask = list.get(index - 1);

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
     * @param index the index of the task to delete
     * @return a String representing the deleted item.
     */
    public static String delete(int index) {
        Task goneTask = list.get(index - 1);
        list.remove(goneTask);

        return "Alright, I have removed the following task from your list:\n" + goneTask
                + "\nNow you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Finds related items and their index on the user's current list.
     *
     * @param keyWord the keyWord to search for.
     * @return a String representing the found item.
     */
    public static String find(String keyWord) {
        return ui.printFound(keyWord, list);
    }
}
