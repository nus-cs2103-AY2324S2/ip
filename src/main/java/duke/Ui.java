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
        return "[quack] I don't understand that command.\n To add a task"
                + " please specify with the task type - \"todo\", \"deadline\" or \"event\", space, task"
                + " name.\n For deadlines, please add a /by followed by the deadline (eg. /by 2/12/2019 1800)."
                + " For events, please add a / followed by the start time,\n"
                + " then another \"/\" followed by the end time.\n"
                + " To mark or unmark the nth task on the list, enter \"mark n\" or \"unmark n\" "
                + "where n is the number on the list.\n"
                + " To delete the nth task on the list, \"delete n\" where "
                + "n is the number on the list.\n"
                + " If you want to list your procrastinations, please enter the word \"list\"."
                + " Type \"bye\" to leave. \n You can type \"feed bread to bearducky\"?"
                + " [Hopeful quacking]";
    }
}
