package com.example.artemis;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Artemis is a simple task management application.
 * It allows users to add, list, mark as done, and delete tasks.
 */
public class Artemis {
    // File path for storing tasks data
    private static final String FILE_PATH = "./data/artemis.txt";

    // Components of Artemis
    private static final Storage STORAGE = new Storage(FILE_PATH);
    private static final Ui UI = new Ui();
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Artemis class.
     */
    public Artemis() {
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (ArtemisException e) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Artemis application, handling user input and performing tasks.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        UI.showWelcomeMessage();

        while (true) {
            String input = sc.nextLine();
            try {
                // Parse user input and perform corresponding actions
                Parser.parseInput(input, tasks, UI, STORAGE);
            } catch (ArtemisException e) {
                UI.showError("Oops, there might be invalid input..");
            }
            // Check if the user entered "bye" to exit the application
            if (input.contains("bye")) {
                break;
            }
        }
        sc.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Main method to start the Artemis application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Artemis().run();
    }
}
