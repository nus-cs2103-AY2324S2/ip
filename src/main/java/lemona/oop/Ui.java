package lemona.oop;

/**
 * Represents the user interface of the Lemona task manager application.
 * Ui handles user interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
    }

    /**
     * Displays an error message when there's an issue loading tasks from a file.
     */
    public String showLoadingError() {
        return "Sorry, I think I haven't had enough vitamin C."
                + "\nThere was an error loading file, so I had to make a new taskList for you!"
                + "\nI will need to go have some LEMONA.";
    }

    public String showDateTimeParsingError() {
        return "I think you haven't had enough vitamin C."
                + "\nYour time format should be :"
                + "\n\t{ dd/MM/yyyy HHmm }"
                + "\nI suggest you take some LEMONA.";
    }
}
