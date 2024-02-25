package dav;

/**
 * Handles user interface interactions such as greeting, getting user input, and displaying messages.
 */
class Ui {

    /**
     * Displays a greeting message to the user.
     */
    public String greetUser() {
        return "What's up! I'm Dav.\nHow may I help you?";
    }

    /**
     * Displays an error message when there is an issue loading tasks from a file.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }
}

