package view;

/**
 * The {@code greetView} class represents a view for displaying a welcome message upon starting the application.
 */
public class GreetView extends Ui {

    /**
     * Displays a welcome message when the program is started.
     */
    @Override
    public void display() {
        System.out.println(
            "              ░░  ░░░░░░   ░░░░░░  ░░    ░░    ░░\n"
            + "              ▒▒ ▒▒       ▒▒       ▒▒     ▒▒  ▒▒\n"
            + "              ▒▒ ▒▒   ▒▒▒ ▒▒   ▒▒▒ ▒▒      ▒▒▒▒\n"
            + "              ▓▓ ▓▓    ▓▓ ▓▓    ▓▓ ▓▓       ▓▓\n"
            + "              ██  ██████   ██████  ███████  ██\n"
            + "____________________________________________________________\n"
            + "   Hello! I'm Iggly, your personal assistant chatbot.\n"
            + "   What can I do for you?\n"
            + "____________________________________________________________\n"
        );
    }
}
