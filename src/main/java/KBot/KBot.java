package KBot;

import java.util.Scanner;
import java.io.IOException;

import actions.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
import ui_design.Messages;

/**
 * Encapsulate a chatbot names kaipybara that takes in input from the user and
 * perform tasks such as creating a todo list.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class KBot {

    /**
     * Simulate what goes on in the chatbot.
     */
    public static void simulate() {
        Scanner sc = new Scanner(System.in); // read inputs from user
        while (true) {
            String userInput = sc.nextLine();
            System.out.println(Messages.getLine());
            if (userInput.equals("bye")) { // stops the program
                break;
            } else {
                try {
                    Command c = Parser.parse(userInput);
                    System.out.println(c.execute());
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (InvalidInputException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (InvalidCommandException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            System.out.print(Messages.getLine());
        }
        sc.close();
    }
}
