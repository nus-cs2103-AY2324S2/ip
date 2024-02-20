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
     * Message to be displayed on launching the bot.
     */
    public void launchMessage() {
        System.out.println("----------------------------------------------------------\n"
                + "Hello! I'm Panna.\n"
                + "What can I do for you?\n\n"
                + "----------------------------------------------------------");
    }

    /**
     * Message to be displayed on leaving the bot.
     */

    public void farewellMessage() {
        System.out.println("----------------------------------------------------------\n"
                + "Bye. Hope to see you again soon!\n\n"
                + "----------------------------------------------------------");
    }

    /**
     * Message to be displayed upon calling the list functionality of the bot
     * @param tl
     */
    public void listMessage(TaskList tl) {
        System.out.println("----------------------------------------------------------");
        tl.printList();
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Displays message and marks the relevant task.
     * @param tasks
     * @throws PannaException
     */
    public void mark(TaskList tasks) throws PannaException {
        System.out.println("Which one should I mark? Write the label number :] ");
        try {
            int label = s.nextInt();
            tasks.get(label - 1).setDone(true);

            System.out.println("----------------------------------------------------------");
            System.out.println("Nice! I've marked this task as done: \n"
                    + tasks.get(label - 1));
            System.out.println("----------------------------------------------------------");

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

    public void unmark(TaskList tasks) throws PannaException {
        System.out.println("Which one should I unmark? Write the label number :] ");
        try {
            int label = s.nextInt();
            tasks.get(label - 1).setDone(false);

            System.out.println("----------------------------------------------------------");
            System.out.println("Okay! I've unmarked this task as done: \n"
                    + tasks.get(label - 1));
            System.out.println("----------------------------------------------------------");
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

    public void deleteMessage(TaskList tasks) throws PannaException {
        System.out.println("Which one should I delete? Write the label number :] ");
        try {
            int label = s.nextInt();
            Task t = tasks.get(label - 1);
            tasks.delete(label - 1);

            System.out.println("----------------------------------------------------------");
            System.out.println("Task successfully removed! \n"
                    + t);
            System.out.println("----------------------------------------------------------");

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
    public void todoMessage(TaskList tasks) throws PannaException {
        try {
            System.out.println("What kind of todo? ");
            String input = s.nextLine();
            Task t = new Todo(input);
            t.setDone(false);
            tasks.add(t);
            System.out.println("Got it! I've added the \n" + t + "\n todo!");
            System.out.println("Now you have " + tasks.size() + " task(s) in the list! ");
        } catch (Exception e) {
            throw new PannaException("All inputs must be Strings! Please ensure it is not empty :D");
        }

    }

    /**
     * Displays the message corresponding to a new deadline task.
     * @param tasks
     * @param p
     * @throws PannaException
     */

    public void deadlineMessage(TaskList tasks, Parser p) throws PannaException {
        try {
            System.out.println("What kind of deadline? ");
            String input = s.nextLine();
            System.out.println("When is the deadline? ");
            String deadline = s.nextLine();
            LocalDate dl = p.parse(deadline);

            Task t = new Deadline(input, dl);
            t.setDone(false);
            tasks.add(t);

            System.out.println("Got it! I've added the \n" + t + "\n deadline!");
            System.out.println("Now you have " + tasks.size() + " task(s) in the list! ");
        } catch (Exception e) {
            throw new PannaException("Please ensure all your formats are correct!");
        }


    }

    /**
     * Displays the message corresponding to a new event task.
     * @param tasks
     * @param p
     * @throws PannaException
     */

    public void eventMessage(TaskList tasks, Parser p) throws PannaException {
        try {
            System.out.println("What kind of event? ");
            String input = s.nextLine();
            System.out.println("When does it start? ");
            String start = s.nextLine();
            LocalDate st = p.parse(start);
            System.out.println("When does it end? ");
            String end = s.nextLine();
            LocalDate en = p.parse(end);
            Task t = new Event(input, st, en);
            t.setDone(false);
            tasks.add(t);
            System.out.println("Got it! I've added the \n" + t + "\n event!");
            System.out.println("Now you have " + tasks.size() + " task(s) in the list! ");
        } catch (Exception e) {
            throw new PannaException("Please ensure all your formats are correct! ");
        }

    }

    /**
     * finds the task with the substring provided
     * @param tasks
     * @throws PannaException
     */
    public void find(TaskList tasks) throws PannaException {
        System.out.println("What shall I find for you! ");
        String k = s.nextLine();

        TaskList newList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.contains(k)) {
                newList.add(tasks.get(i));

            }
        }
        System.out.println("The matches are: ");
        newList.printList();
    }
}
