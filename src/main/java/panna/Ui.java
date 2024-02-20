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
        String output = "----------------------------------------------------------\n"
                        + tl.printList()
                        + "\n----------------------------------------------------------";
        return output;
    }

    public String markDone(TaskList tasks, int label) throws PannaException {
        String output = "----------------------------------------------------------\n"
                + "Nice! I've marked this task as done: \n"
                + tasks.get(label - 1)
                + "\n"
                + "----------------------------------------------------------";
        return output;
    }

    public String unmarkDone(TaskList tasks, int label) throws PannaException {
        String output = "----------------------------------------------------------\n"
                + "Nice! I've marked this task as undone: \n"
                + tasks.get(label - 1)
                + "\n"
                + "----------------------------------------------------------";
        return output;
    }
    /**
     * Displays message and marks the relevant task.
     * @param tasks
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
     * Displays message and unmarks the relevant task.
     * @param tasks
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
     * Deletes the relevant task and displays a message.
     * @param tasks
     * @throws PannaException
     */

    public String delete(TaskList tasks, int label) throws PannaException {
        try {
            Task t = tasks.get(label - 1);
            tasks.delete(label - 1);

            String output = "----------------------------------------------------------\n"
                    + "Task successfully removed! \n"
                    + t + "\n"
                    + "----------------------------------------------------------";
            return output;

        } catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is "
                    + tasks.size()
                    + "\nPlease try with a more appropriate value! ");
        }
    }

    /**
     * Displays a message corresponding to a new Todo task.
     * @param tasks
     * @throws PannaException
     */
    public String todo(TaskList tasks, String input) throws PannaException {
        try {
            Task t = new Todo(input);
            t.setDone(false);
            tasks.add(t);
            String output = "Got it! I've added the \n" + t + "\n todo!\n"
                    + "Now you have " + tasks.size() + " task(s) in the list! ";
            return output;
        } catch (Exception e) {
            return "The task may already be in the list!";
        }

    }

    /**
     * Displays the message corresponding to a new deadline task.
     * @param tasks
     * @param p
     * @throws PannaException
     */

    public String deadline(TaskList tasks, String input, LocalDate dl) throws PannaException {
        try {


            Task t = new Deadline(input, dl);
            t.setDone(false);
            tasks.add(t);

            String output = "Got it! I've added the \n" + t + "\n deadline!\n"
                    + "Now you have " + tasks.size() + " task(s) in the list! ";
            return output;
        } catch (Exception e) {
            return "The task may already be in the list!";
        }


    }

    /**
     * Adds event and returns String output
     * @param tasks
     * @param event
     * @param st
     * @param end
     * @return
     * @throws PannaException
     */

    public String update(TaskList tasks, String taskn, String changedName) throws PannaException {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.equals(taskn)) {
                tasks.get(i).setTaskName(changedName);
                return "Name changed successfully!";
            }
        }

        return "No task of such name found!";
    }
    public String event(TaskList tasks, String event, LocalDate st, LocalDate end) throws PannaException {
        try {

            Task t = new Event(event, st, end);
            t.setDone(false);
            tasks.add(t);

            String output = "Got it! I've added the \n" + t + "\n event!\n"
                    + "Now you have " + tasks.size() + " task(s) in the list! ";
            return output;
        } catch (Exception e) {
            return "The task may already be in the list!";
        }

    }

    /**
     * finds the task with the substring provided
     * @param tasks
     * @throws PannaException
     */
    public String find(TaskList tasks, String k) throws PannaException {

        TaskList newList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.contains(k)) {
                newList.add(tasks.get(i));
            }
        }

        return "The matches are: \n"
                + newList.printList();
    }
}
