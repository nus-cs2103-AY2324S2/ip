package duke;

import duke.task.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Responsible for handling Input Output tasks in the application.
 */
public class IOHandler {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs IoHandler Object.
     */
    public IOHandler()  {
    }

    public void displaySearchResults(ArrayList<Task> list) {
        divider();
        System.out.println("Here are the matching tasks in your list: ");
<<<<<<< HEAD
        for (int j = 0; j < list.size(); j++) {
            System.out.println(j+1 + "." + list.get(j));
=======
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i));
>>>>>>> branch-Level-9
        }
        divider();
    }


    /**
     * Displays welcome Message.
     */
    public void welcomeMessage() {
        divider();
        System.out.println("Hello! I'm Shaunbot");
        System.out.println("What can I do for you?");
        divider();
    }

    /**
     * Divides the paragraphs.
     */
    public void divider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the input from the user
     * @return User's inputted String.
     */
    public String typeMessage() {
        return sc.nextLine();
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public void display(TaskList taskList) {
        int count = 0;
        int serial = 1;
        divider();
        while (count < taskList.size()) {
            System.out.println(serial + "." + taskList.get(count));
            count++;
            serial++;
        }
        divider();
    }
    /**
     * Displays an exit message.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon !");
        divider();
    }

    /**
     * Displays a confirmation message when some task gets added.
     * @param t
     * @param taskList
     */
    public void echoAdd(Task t, TaskList taskList) {
        divider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        taskList.size();
        divider();
    }

}