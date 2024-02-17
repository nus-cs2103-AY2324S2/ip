package iggly.view;

/**
 * The {@link GreetView} class represents a view for displaying a welcome message upon starting the application.
 */
public class GreetView extends Ui {

    /**
     * Displays a welcome message when the program is started.
     */
    @Override
    public String display() {
        return (
                "   Hello! I'm Iggly, your personal assistant chatbot.\n"
                + "   What can I do for you? \uD83D\uDC27\n\n"
                + "   Enter 'help' to view all the available commands!");
    }
}
