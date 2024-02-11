package tsundere.ui;

import tsundere.exception.GeneralException;

/**
 * Encapsulates a UI object that generates a response from user input.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Initializes Parser to read commands from command line for execution.
     *
     * @return response to given input.
     */
    public String chat(String input) {

        String response;

        Parser parser = new Parser(input);
        try {
            response = parser.execute();
        } catch (GeneralException e) {
            response = e.toString();
        }
        return response;
    }
}
