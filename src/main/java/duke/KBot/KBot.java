package duke.kbot;

import duke.actions.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputException;

import java.io.IOException;

/**
 * Encapsulate a chatbot names kaipybara that takes in input from the user and
 * perform tasks such as creating a todo list.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class KBot {
    /**
     * Empty constructor for JavaFX. JavaFX creates the Application instance by
     * calling the no-argument constructor.
     */
    public KBot() {
    }

    /**
     * Simulate what goes on in the chatbot.
     */
    public static String simulate(String userInput) {
        assert userInput != null && userInput.length() > 0 : "Cannot simulate: no user input!";
        if (userInput.equals("bye")) { // stops the program
            return "";
        } else {
            try {
                Command c = Parser.parse(userInput);
                return c.execute();
            } catch (IOException e) {
                return ("Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                return ("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                return ("Error: " + e.getMessage());
            } catch (InvalidCommandException e) {
                return ("Error: " + e.getMessage());
            }
        }
    }
}
