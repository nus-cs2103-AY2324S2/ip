package utilities;

import java.util.Scanner;

import commands.Commands;
import exceptions.WilliamException;

/**
 * The Ui class deals with interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Adds the opening message in the William Chatbot
     */
    public void openingTitle() {
        String logo = "William";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Returns the enum command
     * 
     * @param input Input that is a command in String format
     * @return output Output that is a command in Commands format
     * @throws WilliamException If the command does not exist in the Commands class
     */
    public Commands retrieveCommand(String input) throws WilliamException {
        try {
            return Commands.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new WilliamException("This command " + input + " does not exist, please try again!");
        }
    }

    /**
     * Interact with the user and operate based on their input
     * 
     * @param parser Contains the list of tasks and storage class
     */
    public void interactWithUser(Parser parser) {
        while (true) {
            String input = sc.nextLine();
            Commands command = null;
            String[] texts = AdditionalInfoParser.retrieveTexts(input);
            try {
                command = retrieveCommand(texts[0]);
            } catch (WilliamException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }

            boolean checkExit = parser.parseCommands(command, texts[1]);
            if (checkExit == false) {
                break;
            }
        }
    }
}
