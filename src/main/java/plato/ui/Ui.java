package plato.ui;


import java.util.Random;

/**
 * Commandline GUI class to handle all the printing in the application.
 */

public class Ui {
    private static final String MESSAGE_BYE = " Bye. Hope to see you again soon!";
    private static final String INDENT = " ";
    private static final String FIRST = " Hello! I'm Plato";
    private static final String SECOND = " What do you seek?";
    private static final String[] GREET = new String[]{FIRST, SECOND};

    private static final String[] CHEESY_LINES =
            {"In the realm where shadows dance upon the walls of perception, truth emerges, veiled in the enigma of "
             + "existence.",
                    "In the labyrinth of the mind, where ideals clash and philosophies intertwine, enlightenment "
                    + "awaits those who dare to seek.",
                    "Beneath the canopy of stars, amidst the eternal dance of ideas, lies the essence of our quest "
                    + "for understanding.",
                    "Like a philosopher's stone, the depths of wisdom hold the power to transform even the darkest of"
                    + " doubts into the brightest of truths.",
                    "In the symphony of thought, the dialectic of reason and passion orchestrates the melody of our "
                    + "journey through the boundless cosmos."};

    private Ui() {

    }

    /**
     * Display the opening line of the chatbot,based on its personality.
     *
     * @return A string to display to the UI.
     */
    public static String saySmartStuff() {
        Random rand = new Random();
        return CHEESY_LINES[rand.nextInt(CHEESY_LINES.length)];
    }

    /**
     * Displays the welcome greeting of the chatbot.
     */
    public static String sayWelcome() {
        StringBuilder returnString = new StringBuilder();
        for (String l : GREET) {
            returnString.append(INDENT).append(l);
            returnString.append("\n");
        }
        return returnString.toString();
    }

    /**
     * Make the ui say goodbye.
     *
     * @return returns the by message.
     */
    public static String sayBye() {
        return MESSAGE_BYE;
    }

    /**
     * Converts an Arraylist String to its output string.
     *
     * @param items An ArrayList containing items to convert/
     * @return A String with each item added onto a new line
     */
    public static String convertToString(String... items) {
        StringBuilder ret = new StringBuilder();
        for (String item : items) {
            ret.append(INDENT).append(item);
            ret.append("\n");
        }
        return ret.toString();

    }

    /**
     * Decides what error message to show based on the PlatoException error message
     *
     * @param msg PlatoException error message.
     * @return The error message.
     */
    public static String handleError(String msg) {

        String errorResponse;
        switch (msg) {
        case "descriptionError":
            errorResponse = " OOPS!!! The description of this command cannot be empty.";
            break;
        case "fromEmptyError":
            errorResponse = " OOPS!!! the from date cannot be empty ";
            break;
        case "byEmptyError":
            errorResponse = " OOPS!!! the by date cannot be empty.";
            break;
        case "deadlineError":
            errorResponse = "OPPSSSS!!!! Your dateline command is incorrect format";
            break;
        case "eventError":
            errorResponse = "OPPPPssss!!! YOur event command is of the invalid format!!";
            break;
        case "manageError":
            errorResponse = "Opppps!!!! Invalid manage action ";
            break;
        case "queryError":
            errorResponse = "Query invalid search command !";
            break;
        case "dateError":
            errorResponse = "OOPS!!! Incorrect date format!!!";
            break;
        case "loadError":
            errorResponse = "Error loading the save file";
            break;
        case "numberError":
            errorResponse = "OOPS!!! This is missing your index number";
            break;
        case "outOfRangeError":
            errorResponse = "Opps!!!!! Your index in out of range!";
            break;
        case "emptyListError":
            errorResponse = "Opps!!!!! Your list is empty!!!You can't do any of these actions yet!";
            break;
        case "dateOutOfRangeError":
            errorResponse = "Ooops!!! Yoo Your date numbers ain't right";
            break;
        case "directoryError":
            errorResponse = "Error Creating directory!!!";
            break;
        case "invalidError":
            errorResponse = "OOPS!!! I'm sorry, incorrect command or input";
            break;
        default:
            errorResponse = "Invalid application, commence self-destruct >:(";
        }
        return errorResponse;
    }

}
