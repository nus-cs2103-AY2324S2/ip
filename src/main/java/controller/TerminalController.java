package controller;

import model.Feedback;
import service.FeedbackService;

import java.util.Scanner;

public class TerminalController {
    private FeedbackService feedbackService = new FeedbackService();

    public void printWelcomeMessage() {
        System.out.println(feedbackService.getWelcomeMessage());
    }

    public void listen() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        while (!stop) {
            String curInput = scanner.nextLine();
            Feedback feedback = feedbackService.run(curInput);

            System.out.println(feedback);

            if (feedback.getIsBye()) {
                stop = true;
            }
        }
    }
}