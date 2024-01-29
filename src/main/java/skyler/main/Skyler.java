package skyler.main;

import java.util.Scanner;
import skyler.exception.SkylerException;

public class Skyler {
    private static final String CHATBOT_NAME = "Skyler";

    public static void main(String[] args) {
        Ui.printGreeting(CHATBOT_NAME);

        Storage.loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = Ui.getUserInput(scanner);

            if (userInput.equals("bye")) {
                Ui.printByeMessage();
                scanner.close();
                Storage.saveTasksToFile(); // Save tasks before exiting
                break;
            }

            try {
                Parser.processUserInput(userInput);
            } catch (SkylerException e) {
                Ui.printErrorMessage(e.getMessage());
            }
        }
        scanner.close();
    }
}

