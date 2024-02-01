package duke;

/**
 * Represents the user interface for interacting with the Duke application.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui () {
    }

    /**
     * Displays an error message for invalid file path during loading.
     */
    public void showLoadingError() {
        System.out.println("Invalid filepath.");
    }

    /**
     * Displays a greeting message when the application starts.
     */
    public void greet() {
        System.out.println("Quack! My name is Bearducky. I am a duck with a bear hat and a baseball bat." +
                "How may I help you today?");
    }

    /**
     * Displays a happy quacking message.
     */
    public void happy() {
        System.out.println("[happy quacking]");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void goodbye() {
        System.out.println("[sad quacking] Can I have some bread before you go?");
    }

    /**
     * Displays an instruction message for the user.
     */
    public void instructionMessage() {
        System.out.println("[quack] I don't understand that command.\n If you would like to add a task to the Duckalendar," +
                " please specify with the task type - \"todo\", \"deadline\" or \"event\" then space then task" +
                " name.\n For deadlines, please add a /by followed by the deadline (eg. /by 2/12/2019 1800) . For events, please add a / followed by the start time,\n" +
                " then another \"/\" followed by the end time.\n" +
                " To mark or unmark the nth task on the list, enter the command \"mark n\" or \"unmark n\" where n is the number on the list.\n" +
                " To delete the nth task on the list, enter the command \"delete n\" where n is the number on the list.\n" +
                " If you would like me to list the things you are procrastinating, please enter the word \"list\"." +
                " \n If you would like to leave, please enter the word \"bye\". \n Do also feel free to also type the words \"feed bread to bearducky\"?" +
                " [Hopeful quacking]");
    }
}
