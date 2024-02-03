package ken.ui;

/**
 * The Ui class is responsible for handling user interface-related messages.
 * It provides methods to display welcome and goodbye messages to the user.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void welcomeMessage() {
        System.out.println("Hi Barbie!");
        System.out.println("I'm Ken!");
        System.out.println("What would you like to beach today?\n");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void byeMessage() {
        System.out.println("Beach off!\n");
    }
}
