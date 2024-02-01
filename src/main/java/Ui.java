import java.util.Scanner;

public class Ui {

    private final Scanner myScannerObj;
    private final String horizontalLine = "____________________________________________________________\n";

    public Ui() {
        this.myScannerObj = new Scanner(System.in);
    }

    public void greetUser() {
        this.formatReply("Hello! I'm Zenify\n" + " What can I do for you?");
    }

    public void formatReply(String raw) {
        System.out.println(this.horizontalLine + " " + raw + "\n" + this.horizontalLine);
    }

    public void formatError(String raw) {
        System.err.println(this.horizontalLine + " " + raw + "\n" + this.horizontalLine);
    }

    public void exit() {
        this.formatReply("Bye. Hope to see you again soon!");
    }

    public void displayError(String errorMessage) {
        this.formatError("Error: " + errorMessage);
    }

    public String getInput() {
        return myScannerObj.nextLine();
    }

    public boolean next() {
        return myScannerObj.hasNextLine();
    }

    public void closeScanner() {
        myScannerObj.close();
    }

}
