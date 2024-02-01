import java.util.ArrayList;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm BotChat\n What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public void showErrorMessage(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________\n");
    }
}
