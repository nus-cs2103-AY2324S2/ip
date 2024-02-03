package echon;

import java.util.ArrayList;

/**
 * Represents the user interface which Echon uses.
 */
public interface EchonUi {
    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void displayEchonMessage(String message);

    /**
     * Prints a multi-line message to the user.
     *
     * @param messages The list of message lines to be printed.
     */
    public void displayEchonMessages(ArrayList<String> messages);
}
