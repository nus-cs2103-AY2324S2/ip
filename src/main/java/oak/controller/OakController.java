package oak.controller;

import java.io.IOException;

import oak.exceptions.InvalidInputException;
import oak.feedback.FeedbackService;

/**
 * The type Oak Controller.
 */
public class OakController {
    /** FeedbackService instance to be used */
    private FeedbackService feedbackService = new FeedbackService();

    /**
     * Gets the appropriate response for the user's input and returns it
     *
     * @param input
     * @return the formatted String response
     */
    public String getResponse(String input) {
        try {
            return feedbackService.run(input);
        } catch (InvalidInputException | IOException e) {
            return e.getMessage();
        }
    }
}
