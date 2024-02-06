import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcome() {
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello! I'm EchoPilot.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public void showGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(goodbyeMessage);
    }

    public void showError(String message) {
        String errorMessage = "____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________";
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    public void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
