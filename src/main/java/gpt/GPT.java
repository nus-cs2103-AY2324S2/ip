package gpt;

import java.util.Scanner;

import java.io.IOException;



public class GPT {
    private static final String FILE_PATH = "./gpt/data/GPT.txt"; //for packages, need to change this path to root????


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(FILE_PATH);
        TaskList tl = storage.loadTasks();

        ui.printWelcomeMsg();

        Scanner scn = new Scanner(System.in);

        while (true) {
            String userInput = scn.nextLine().trim();
            if (userInput.equals("bye")) {
                ui.printGoodbyeMessage();
                break;
            }

            try {
                Parser.parseCommand(userInput, tl, ui, storage);
            } catch (GPTException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }

    }
}

