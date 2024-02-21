package oak.controller;

import java.io.IOException;

import oak.exceptions.InvalidInputException;
import oak.feedback.FeedbackService;
import oak.task.ReminderService;

public class OakController {
    private FeedbackService feedbackService = new FeedbackService();

    public String getResponse(String input) {
        try {
            return feedbackService.run(input);
        } catch (InvalidInputException | IOException e) {
            return e.getMessage();
        }
    }
}
