package panna;

import java.time.LocalDate;
import java.util.Scanner;


/**
 * Encapsulates all the Ui elements of the bot.
 */
public class Ui {
    private Scanner s;

    /**
     * Constructor method which initializes scanner
     */
    public Ui() {
        s = new Scanner(System.in);
    }




    /**
     * Message to be displayed upon calling the list functionality of the bot
     * @param tl
     */
    public String listMessage(TaskList tl) {
        String intro = "Fine... Your list is \n";
        String output = "----------------------------------------------------------\n"
                        + tl.printList()
                        + "\n----------------------------------------------------------";
        return intro + output;
    }

    /**
     * Returns the message when a task is finished that the bot replies with
     * @param tasks
     * @param label
     * @return String message
     * @throws PannaException
     */

    public String markDone(TaskList tasks, int label) throws PannaException {
        String output = "----------------------------------------------------------\n"
                + "Congratulations on getting done with the task \n"
                + tasks.get(label - 1)
                + "\n"
                + "Have a cookie! [o]\n"
                + "----------------------------------------------------------";
        return output;
    }

    /**
     * Returns the message the bot replies with when a task is unmarked.
     * @param tasks
     * @param label
     * @return String message
     * @throws PannaException
     */
    public String unmarkDone(TaskList tasks, int label) throws PannaException {
        String output = "----------------------------------------------------------\n"
                + "Awwwww Man we were doing so well!\nI've marked this task as undone: \n"
                + tasks.get(label - 1)
                + "\n"
                + "I take back my cookie :[   Me <- [o] <- you\n"
                + "----------------------------------------------------------";
        return output;
    }

    /**
     * Marks the task at position label in the tasklist as done.
     * @param tasks
     * @param label
     * @throws PannaException
     */
    public void mark(TaskList tasks, int label) throws PannaException {

        try {
            tasks.get(label - 1).setDone(true);

        } catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is "
                    + tasks.size()
                    + "\nPlease try with a more appropriate value! ");
        }
    }

    /**
     * Unmarks the task in position label in the taskList.
     * @param tasks
     * @param label
     * @throws PannaException
     */

    public void unmark(TaskList tasks, int label) throws PannaException {

        try {

            tasks.get(label - 1).setDone(false);

        } catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is"
                    + tasks.size()
                    + "\nPlease try with a more appropriate value! ");
        }

    }

    /**
     * Deletes the task in position label and returns the String
     * @param tasks
     * @param label
     * @return String output from a delete operation.
     * @throws PannaException
     */

    public String delete(TaskList tasks, int label) throws PannaException {
        try {
            Task t = tasks.get(label - 1);
            tasks.delete(label - 1);

            String output = "----------------------------------------------------------\n"
                    + "Goodbye task \n"
                    + t + "\n"
                    + "You were a good task while you lasted :(\n"
                    + "----------------------------------------------------------";
            return output;

        } catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is "
                    + tasks.size()
                    + "\nPlease try with a more appropriate value! ");
        }
    }

    /**
     * Initializes a todo and returns the String confirmation
     * @param tasks
     * @param input
     * @return string confirmation.
     * @throws PannaException
     */
    public String todo(TaskList tasks, String input) throws PannaException {
        try {
            Task t = new Todo(input);
            t.setDone(false);
            tasks.add(t);
            String output = "I've added the \n" + t + "\n todo!\nYou better do it!\n"
                    + "Now you have " + tasks.size() + " task(s) in the list! ";
            return output;
        } catch (Exception e) {
            return "The task may already be in the list!";
        }

    }

    /**
     * Initializes a deadline and returns a string confirmation.
     * @param tasks
     * @param input
     * @param dl
     * @return string confirmation.
     * @throws PannaException
     */

    public String deadline(TaskList tasks, String input, LocalDate dl) throws PannaException {
        try {


            Task t = new Deadline(input, dl);
            t.setDone(false);
            tasks.add(t);

            String output = "Got it! I've added the \n" + t + "\n deadline!\nIf you don't do it on time I will be mad\n"
                    + "Now you have " + tasks.size() + " task(s) in the list! ";
            return output;
        } catch (Exception e) {
            return "The task may already be in the list!";
        }


    }

    /**
     * updates the task with name taskn to changedName.
     * @param tasks
     * @param taskn
     * @param changedName
     * @return String output after updating.
     * @throws PannaException
     */
    public String update(TaskList tasks, String taskn, String changedName) throws PannaException {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.equals(taskn)) {
                tasks.get(i).setTaskName(changedName);
                return "We have updated the name from\n"
                        + taskn + "\nTo\n" + changedName;
            }
        }

        return "Please check the list before making me do work for no reason ;(";
    }

    /**
     * Initializes an event and returns the String confirmation.
     * @param tasks
     * @param event
     * @param st
     * @param end
     * @return string confirmation
     * @throws PannaException
     */
    public String event(TaskList tasks, String event, LocalDate st, LocalDate end) throws PannaException {
        try {
            if (st.isAfter(end)) {
                return "Your start is after your end. That is not possible";
            }

            Task t = new Event(event, st, end);
            t.setDone(false);
            tasks.add(t);

            String output = "Woah! The event\n" + t + " has been added!\nI hope you're excited!\n"
                    + "Now you have " + tasks.size() + " task(s) in the list! ";
            return output;
        } catch (Exception e) {
            return "The task may already be in the list!";
        }

    }

    /**
     * Finds the tasks in the tasklist with substring k
     * @param tasks
     * @param k
     * @return collection of tasks.
     * @throws PannaException
     */
    public String find(TaskList tasks, String k) throws PannaException {

        TaskList newList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.contains(k)) {
                newList.add(tasks.get(i));
            }
        }

        if (newList.size() == 0) {
            return "Ugh I could not find any matches. Try again!";
        }

        return "Woah! We found the following matches. \n"
                + newList.printList()
                + "\nWho knew a bot could play matchmaker :]";
    }
}
