package lamball;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import lamball.task.Deadline;
import lamball.task.Event;
import lamball.task.Task;
import lamball.task.ToDo;



/**
 * A class that handles the to-do list operations.
 *
 * @author ongzhili
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    private String lastDoneTask;


    /**
     * Constructor for TaskList
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.lastDoneTask = "";
    }

    /**
     * Returns size of task list.
     *
     * @return Intger value of size of task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns last done task.
     *
     * @return last done task in String format.
     */
    public String getLastDoneTask() {
        return this.lastDoneTask;
    }

    private void printList(ArrayList<Task> lst) {
        String listStr = "Here aaaaare the taaaasks in your list:";
        for (int i = 0; i < lst.size(); i++) {
            listStr += "\n    " + (i + 1) + ". " + lst.get(i).toString() + "";
        }
        lastDoneTask = listStr;
    }

    /**
     * Default list printing operation.
     *
     * @return Boolean to continue keeping the bot running.
     */
    public boolean printList() {
        printList(this.tasks);
        return true;
    }

    /**
     * Marks a given task from the list.
     *
     * @param idx Index number of task in list.
     * @return Boolean to continue keeping the bot running.
     */
    public boolean mark(int idx, boolean isInit) {
        Task temp = tasks.get(idx);
        temp.mark();
        lastDoneTask = "I have maaarked the task as done:\n" + "    " + temp.toString();
        if (!isInit) {
            Storage.replaceLine("1 | " + temp.command(), idx);
        }
        return true;
    }

    /**
     * Unmarks a given task from the list.
     *
     * @param idx Index number of task in list.
     * @return Boolean to continue keeping the bot running.
     */
    public boolean unMark(int idx) {
        Task temp = tasks.get(idx);
        temp.unMark();
        lastDoneTask = "I have maaarked the task as undone:\n" + "    " + temp.toString();
        Storage.replaceLine("0 | " + temp.command(), idx);
        return true;
    }

    /**
     * Creates a deadline and adds it to the list of tasks.
     *
     * @param arg Arguments to create with.
     * @param isInit Whether this is run during initialization
     * @return Boolean to continue keeping the bot running.
     */
    public boolean toDo(String arg, boolean isInit) {
        Task temp = new ToDo(arg);
        tasks.add(temp);
        lastDoneTask = "Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size()
                + " tasks in the list.";
        if (!isInit) {
            Storage.writeToFile("0 | " + temp.command());
        }
        return true;
    }

    /**
     * Creates a deadline and adds it to the list of tasks.
     *
     * @param furtherSplit Arguments to create with.
     * @param isInit Whether this is run during initialization
     * @return Boolean to continue keeping the bot running.
     */
    public boolean deadline(String[] furtherSplit, boolean isInit) {
        try {
            Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
            tasks.add(temp);
            lastDoneTask = "Added Deadline:\n        " + temp.toString() + "\n    Now you have "
                    + tasks.size() + " tasks in the list.";
            if (!isInit) {
                Storage.writeToFile("0 | " + temp.command());
            }
        } catch (DateTimeParseException e) {
            this.lastDoneTask = "Date is in the wrong formaaaaaaat, baa. :(\n    Correct fo" + "rmaaat is: "
                    + "yyyy-mm-dd (e.g 2001-01-20)";
            return true;
        }
        return true;
    }

    /**
     * Creates an event and adds it to the list of tasks.
     *
     * @param furtherSplit Arguments to create with.
     * @param isInit Whether this is run during initialization
     * @return Boolean to continue keeping the bot running.
     */
    public boolean event(String[] furtherSplit, boolean isInit) {
        try {
            Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""),
                    furtherSplit[2].replaceFirst("to ", ""));
            tasks.add(temp);
            lastDoneTask = "Added Event:\n        " + temp.toString() + "\n    Now you have "
                    + tasks.size() + " tasks in the list.";
            if (!isInit) {
                Storage.writeToFile("0 | " + temp.command());
            }
        } catch (DateTimeParseException e) {
            this.lastDoneTask = "Dates are in the wrong formaaaaaaat, baa. :(\n    Correct fo"
                    + "rmaaat is: yyyy-mm-dd (e.g 2001-01-20)";
            return true;
        }
        return true;
    }

    /**
     * Deletes a given task from the list.
     *
     * @param idx index to delete from.
     * @return Boolean that keeps the bot running.
     */
    public boolean deleteFromList(int idx) {
        Task temp = tasks.remove(idx);
        Storage.deleteLine(idx);
        lastDoneTask = "I have removed this taaask:\n" + "        " + temp.toString() + "\n    Now you have "
                + tasks.size() + " tasks in the list.";
        return true;
    }

    /**
     * Finds related tasks based on the prompt provided.
     *
     * @param toFind String prompt to search with
     * @return Boolean that keeps the bot running.
     */
    public boolean find(String toFind) {
        ArrayList<Task> positives = new ArrayList<>();
        for (Task temp : tasks) {
            if (temp.containing(toFind)) {
                positives.add(temp);
            }
        }
        printList(positives);
        return true;
    }

}
