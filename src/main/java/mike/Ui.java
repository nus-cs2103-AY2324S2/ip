package mike;

/**
 * Ui is the class responsible for the user interface, i.e., user input and response.
 * @author ningc
 */
public class Ui {
    /**
     * Displays the object to the user.
     * @param object to be displayed.
     */
    public static void display(Object object) {
        System.out.println(object);
    }

    /**
     * Displays errorMessage to the user.
     * @param errorMessage to be displayed.
     */
    public static void displayError(String errorMessage) {
        display(errorMessage);
    }

    private void displayGreeting() {
        String greeting =
                " Hello! I'm mike WAZOWSKI.\n"
                + " What can I do for you?";
        display(greeting);
    }

}
