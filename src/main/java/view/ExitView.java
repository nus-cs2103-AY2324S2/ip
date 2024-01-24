package view;

/**
 * The {@code ExitView} class represents a view for displaying a farewell message upon exiting the application.
 */
public class ExitView extends Ui {

    /**
     * Displays a farewell message when the program is terminated.
     */
    @Override
    public void display() {
        System.out.println(
            "____________________________________________________________\n"
            + " Bye. Hope to see you again soon!\n"
            + "____________________________________________________________"
        );
    }
}
