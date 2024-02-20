package duke;

/**
 * Represents the user interface for interacting with the Duke application.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Displays an error message for invalid file path during loading.
     */
    public String showLoadingError() {

        return "Invalid filepath.";
    }

    /**
     * Displays a greeting message when the application starts.
     */
    public String greet() {
        return "Quack! My name is Bearducky. I am a duck with a bear hat and a baseball bat."
                + "How may I help you today?";
    }

    /**
     * Displays a happy quacking message.
     */
    public String happy() {

        return "[happy quacking]";
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public String goodbye() {
        return "[sad quacking] Can I have some bread before you go?";
    }

    /**
     * Displays an instruction message for the user.
     */
    public String instructionMessage() {
        return "[quack] I don't understand that command. Use the keywords todo, deadline and event to create tasks.\n\n"
                + "Use the keywords mark, unmark or delete along with the number to mark/unmark/delete tasks.\n\n"

                + "Enter \"list\" to list your tasks or type \"bye\" to leave.\n\n"
                + "You can type \"feed bread to bearducky\"?"
                + " [Hopeful quacking]";
    }
}
