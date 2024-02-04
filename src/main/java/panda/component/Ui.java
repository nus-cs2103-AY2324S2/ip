package panda.component;
import java.util.Scanner;

public class Ui {

    private Scanner myScanner;

    /**
     * Constructs a new Ui.
     * Initializes the Scanner for reading user inputs.
     */
    public Ui() {
        myScanner  = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
    }

    /**
     * Displays a goodbye message to the user and closes the Scanner.
     */
    public void showBye() {
        System.out.println(
            "Bye. Hope to see you again soon!"
        );
        myScanner.close();
    }

    /**
     * Displays a message indicating that no storage was found.
     */
    public void showLoadingError() {
        System.out.println(
            "No storage found. Initializing empty list..."
        );
    }

    /**
     * Displays an error message to the user.
     * 
     * @param errString the error message to display.
     */
    public void showError(String errString) {
        System.out.println(
            "An error has occured:\n"
            + "  " + errString
        );
    }

    /**
     * Displays a reply message to the user.
     * 
     * @param replyString the reply message to display.
     */
    public void showReply(String replyString) {
        System.out.println(replyString);
    }

    /**
     * Reads a command from the user.
     * 
     * @return the command entered by the user.
     */
    public String readCommand() {
        System.out.print("> ");
        String userInput = myScanner.nextLine();
        return userInput.trim();
    }

    // private void reply(String replyString) {
    //     System.out.println(replyString);
    // }

    /**
     * Displays the given TaskList to the user.
     * 
     * @param tlist the TaskList to display.
     */
    public void showList(TaskList tlist) {
        System.out.println(tlist.toString());
    }
}
