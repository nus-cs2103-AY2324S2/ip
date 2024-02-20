package Mitsuki;

import java.util.ArrayList;
/**
 * Contains information on the MitsukiException class.
 *
 * @author Tee Chu Jie
 */
public class MitsukiException extends Exception {

    /**
     * The constructor for a MitsukiException object.
     *
     * @param message Handled by the super constructor.
     */
    public MitsukiException(String message) {
        super(message);
    }

    /**
     * Checks if a command is valid for the Chat Bot.
     * @param command The command to be checked.
     * @throws MitsukiException The exception that will be thrown if command is invalid.
     */
    public static void validate(String command, ArrayList<String> commands) throws MitsukiException {
        if (!commands.contains(command)) {
            throw new MitsukiException("Invalid Command Given");
        }
    }

}
