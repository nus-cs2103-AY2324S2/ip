package goblin;

import goblin.task.Task;
import goblin.exception.OrkException;
import java.util.Scanner;

//Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
public class Ui {
    Scanner input = new Scanner(System.in);
    static String greetings = "Hello, I'm NetGoblin\n"
            + "What can I do for you?";
    static String bye = "Bye. Hope to see you again soon!\n";

    /**
     * read the nextLine of the input
     * @return the nextLine of user input
     */
    public String readCommand() {
        return input.nextLine();
    }


    /**
     * print the greetings message on the screen
     */
    public static void sayHello() {
        line();
        System.out.println(greetings);
        line();
    }

    /**
     * print the goodbye message on the screen
     */
    public static void sayBye() {
        System.out.println(bye);
    }

    /**
     * draw a beautiful line on the screen
     */
    public static void line() {
        System.out.println("--------------------------------");
    }

    /**
     * print the message of a task been successfully added to the list
     * @param task the task added
     * @param size the size of the list after the add
     */
    public void printAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t " + task.notPrint());
        System.out.println("Now you have " + size + (size == 1 ? " task": " tasks") + " in the list.");
    }

    /**
     * print the exception message
     * @param exception the exception to be printed
     */
    public void printException(OrkException exception) {
        String message = exception.getMessage();
        System.out.println("\t " + message);
    }

    /**
     * close the scanner
     */
    public void closeScanner() {
        input.close();
    }

    /**
     * print the message of a task been deleted from the list
     * @param task the task deleted
     * @param index the number of tasks left
     */
    public static void printDeleteMessage(Task task, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("\t " + task.notPrint());
        System.out.println("Now you have " + index + (index == 1 ? " task": " tasks") + " in the list.");
    }

    /**
     *print the message to have marked a task as done
     * @param task the done task
     */
    public static void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t " + task.notPrint());
    }

    /**
     * show loading error
     */
    public void showLoadingError() { System.out.println("I don't know what that means");
    }

    /**
     * a method for the test function
     * @return haha
     */
    public static String forTest() {
        return "haha";
    }
}
