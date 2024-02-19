package iggly.view;

/**
 * The {@link ExitView} class represents a view for displaying a farewell message upon exiting the application.
 */
public class ExitView extends Ui {

    /**
     * Displays a farewell message when the program is terminated.
     */
    @Override
    public String display() {
        return (
            " Bye. Hope to see you again soon! \uD83D\uDC27");
    }
}
