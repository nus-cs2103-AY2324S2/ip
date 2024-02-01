package duke.kbot;

import java.util.Scanner;

import duke.actions.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputException;
import duke.ui_design.Ui;

import java.io.IOException;

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
        Ui ui = new Ui();
        while (true) {
            String userInput = sc.nextLine();
            System.out.println(ui.getLine());
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
            System.out.print(ui.getLine());
        }
        sc.close();
    }
}
