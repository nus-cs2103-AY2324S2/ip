package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class representing the Duke chatbot application.
 *
 * <p>The {@code Duke} class serves as the entry point for the Duke chatbot application.
 * It handles user input, parsing, command execution, and storage operations.</p>
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static final Storage storage = new Storage();

    public Duke() {
    }

    public static void main(String... args) {
        System.out.printf(Messages.WELCOME);

        TaskList tasks;
        try {
            tasks = storage.load();
            System.out.println(tasks);
        } catch (FileNotFoundException e) {
            System.out.printf("Warning: something went wrong when loading the TaskList\n"
                + e.getMessage());
            tasks = new TaskList();
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.printf("\n-> ");
                String userInput = sc.nextLine();

                // Parse user input
                Command command = Parser.parseInput(userInput);

                // Execute the command
                command.execute(tasks);
                storage.save(tasks);

                // Exit condition
                if (command.getCommand().equals("bye")) {
                    System.out.printf(Messages.EXIT);
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reading user input. Exiting.");
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sc.close();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

    }
}
