package oak.controller;

import java.io.IOException;
import java.util.Scanner;

import oak.exceptions.InvalidInputException;
import oak.feedback.FeedbackService;
import oak.feedback.enums.CommandEnum;

/**
 * The type Terminal controller, handles the listening of messages and printing of responses to the terminal
 */
public class TerminalController {
    private FeedbackService feedbackService = new FeedbackService();

    /**
     * Print welcome message and start listening for user inputs
     */
    public void start() {
        System.out.println(this.feedbackService.getWelcomeMessage());
        this.listen();
    }

    /**
     * Start listening for user inputs and prints out the messages obtained from FeedbackService
     * Exits when the user inputs 'bye'
     */
    private void listen() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        String feedback = null;

        while (!stop) {
            String curInput = scanner.nextLine();

            if (curInput.equals(CommandEnum.BYE.getCommandValue())) {
                stop = true;
            }

            try {
                feedback = feedbackService.run(curInput);
            } catch (InvalidInputException | IOException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("----------------------------------------------");
            System.out.println(feedback);
            System.out.println("----------------------------------------------");
        }
    }
}
