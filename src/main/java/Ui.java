import java.util.Scanner;

public class Ui {

    private Scanner myScanner;

    public Ui() {
        myScanner  = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
    }

    public void showBye() {
        System.out.println(
            "Bye. Hope to see you again soon!"
        );
        myScanner.close();
    }

    public void showLoadingError() {
        System.out.println(
            "No storage found. Initializing empty list..."
        );
    }

    public void showError(String errString) {
        System.out.println(
            "An error has occured:\n"
            + "  " + errString
        );
    }

    public void showReply(String replyString) {
        System.out.println(replyString);
    }

    public String readCommand() {
        System.out.print("> ");
        String userInput = myScanner.nextLine();
        return userInput.trim();
    }

    // private void reply(String replyString) {
    //     System.out.println(replyString);
    // }

    public void showList(TaskList tlist) {
        System.out.println(tlist.toString());
    }
}
