package skyler.main;

import java.util.Scanner;
import skyler.exception.SkylerException;

public class Skyler {
    private static final String CHATBOT_NAME = "Skyler";

    public static void main(String[] args) {
        Ui.getGreeting(CHATBOT_NAME);

        Storage.loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = Ui.getUserInput(scanner);

            if (userInput.equals("bye")) {
                Ui.getByeMessage();
                scanner.close();
                Storage.saveTasksToFile(); // Save tasks before exiting
                break;
            }

            try {
                Parser.processUserInput(userInput);
            } catch (SkylerException e) {
                Ui.getErrorMessage(e.getMessage());
            }
        }
        scanner.close();
    }
    
    public String getResponse(String userInput) throws SkylerException {
        if (userInput.equals("bye")) {
            Ui.getByeMessage();
            Storage.saveTasksToFile(); // Save tasks before exiting
            return "Skyler: Bye. Hope to see you again soon!\n";
        }
        return Parser.processUserInput(userInput);
    }

}

