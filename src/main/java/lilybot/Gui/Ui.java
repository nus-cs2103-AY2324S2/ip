package lilybot.Gui;

import lilybot.Task.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ui class handles interactions with users.
 */
public class Ui {

    /**
     * Construct a Ui object.
     */
    public Ui() {

    }

    /**
     * Lists all the tasks to users.
     *
     * @param ls The taskList to be listed.
     * @return A string of all the tasks.
     */
    public String listTask(TaskList ls) {
        StringBuilder s = new StringBuilder("Here're the tasks in ur list: \n");
        int counter = 0;
        for (int i = 0; i < ls.getSize(); i++) {
            counter++;
            Task tk = ls.get(i);
            s.append(counter+ ". "
                    + tk.toString()
                    + "\n");
        }
        return s.toString();
    }


    /**
     * Says goodbye to users.
     */
    public String sayBye() {
        String s = "Bye Bye. See u later! \n" ;
        return s;
    }

    /**
     * Prints to inform users that task is marked done.
     *
     * @param taskString The string of the task to be marked as done.
     * @return A string to inform users that task is marked done.
     */
    public String markDone(String taskString) {
        return "Good job! I've marked this task as done: \n"
                + "  "
                + taskString;
    }

    /**
     * Prints to inform users that task is marked not done.
     *
     * @param taskString The string of the task to be marked as unfinished.
     * @return A string to inform users that task is marked not done.
     */
    public String markNotDone(String taskString) {
       return "Okie, Marked this task as not done yet: \n"
               + "  "
               + taskString;
    }

    /**
     * Prints to inform users that task is added.
     *
     * @param task Specifics of the task to be added.
     * @param taskList The list to be added.
     * @return A statement to inform users that task is added.
     */
    public String printAdded(String task, TaskList taskList) {
        return "  Got it. I've added this task:"+ "\n"
                + "  " + task + "\n"
                + "  Now u have " + taskList.getSize() +
                " tasks in the list.";
    }


    /**
     * Prints to inform users that task is removed.
     *
     * @param taskString The task taht is removed.
     * @param tasklistSize The size of the existing taskList
     * @return A string to inform users that task is removed.
     */
    public String taskRemoved(String taskString, int tasklistSize) {
        return "Noted. The following task is removed:" + "\n"
                + "  " + taskString + "\n"
                + "  Now u have " + tasklistSize +
                " tasks in the list.";
    }


    /**
     * Informs users that the description entered is invalid.
     */
    public String invalidDescription() {
        return "Oops! Sorry, I don't know what that means. Description is empty";
    }

    /**
     * Informs users that the input entered is invalid.
     */
    public String invalidInput() {
        return "Oops! I don't understand the instruction.";
    }

    /**
     * Informs users that the number entered is invalid.
     */
    public String invalidInputNumber() {
        return "Plz tell me which task. I want a valid task number" + "\n";
    }

    /**
     * Informs users that the ddl for Deadline task entered is invalid.
     */
    public String invalidDdlFormat() {
        return "Plz enter a date for the deadline using '/byDate' \n"
                + "Also notice the format should be like this: yyyy-mm-dd'";
    }

    /**
     * Informs users that the date format for
     * the event entered is invalid.
     */
    public String invalidEventFormat() {
        return "Plz enter a date for the event using '/from' and '/to'";
    }

    /**
     * Informs users that the keyWord entered is invalid.
     */
    public String invalidKeyWord() {
        return "Plz enter a valid keyword";
    }

    /**
     * Informs users that no previous command.
     */
    public String noLastCommand() {
        return "This is the first command. Unable to undo.";
    }

    /**
     * Informs users that there is unknown file format.
     */
    public static String botUnknownFormat(int i) {
        i = i + 1;
        return "Oops, I don't understand the file format \n"
                + "Line " + i + " in the given file will be ignored";
    }



}
