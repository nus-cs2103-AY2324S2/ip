package Oak.controller;

import Oak.exceptions.InvalidInputException;
import Oak.Feedback.FeedbackService;
import Oak.Feedback.enums.CommandEnum;

import java.io.IOException;
import java.util.Scanner;

public class TerminalController {
    private FeedbackService feedbackService = new FeedbackService();

    public void start() {
        System.out.println(this.feedbackService.getWelcomeMessage());
        this.listen();
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        String feedback = null;

        while (!stop) {
            String curInput = scanner.nextLine();

            if (curInput.equals(CommandEnum.Bye.getCommandValue())) {
                stop = true;
            }
            else {
                try {
                    feedback = feedbackService.run(curInput);
                }
                catch (InvalidInputException | IOException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            System.out.println("----------------------------------------------");
            System.out.println(feedback);
            System.out.println("----------------------------------------------");
        }
    }
}