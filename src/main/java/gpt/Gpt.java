package gpt;

import javafx.fxml.FXML;

import java.util.Scanner;

/**
 * The main entry-point for the java.duke.Duke application.
 */
public class Gpt {

    private static final String FILE_PATH = "./data/GPT.txt"; //for packages, need to change this path to root
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Boolean isExit = false;
    public Gpt() {
         ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = storage.loadTasks();
        isExit = false;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input) {
        try {
            return Parser.parseCommand(input, tasks, ui, storage);
        } catch (GptException e) {
            return e.getMessage();
        }

    }

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
            } catch (GptException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }

    }
}

