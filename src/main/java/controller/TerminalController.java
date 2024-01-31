package controller;

import exceptions.InvalidInputException;
import model.Feedback;
import service.FeedbackService;

import java.io.IOException;
import java.util.Scanner;

public class TerminalController {
    private FeedbackService feedbackService = new FeedbackService();

    public void printWelcomeMessage() {
        System.out.println(feedbackService.getWelcomeMessage());
    }

    public void listen() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        Feedback feedback = null;

        while (!stop) {
            String curInput = scanner.nextLine();

            try {
                feedback = feedbackService.run(curInput);
            }
            catch (InvalidInputException | IOException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("----------------------------------------------");
            System.out.println(feedback);
            System.out.println("----------------------------------------------");

            if (feedback.getIsBye()) {
                stop = true;
            }
        }
    }
}