package duke;

import commands.Commands;
import exceptions.DukeExceptions;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The NewDuke class represents the core functionality of the Duke application.
 * It handles user interactions, commands parsing, and task management.
 */
public class NewDuke {
    private Storage storage;
    private Ui ui;
    private Save saver;
    private Parser parser;

    /**
     * Constructs a NewDuke instance with the specified file path for data storage.
     *
     * @param filePath The file path for data storage.
     */
    public NewDuke(String filePath) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        ui = new Ui();
        storage = new Storage();
        saver = new Save(filePath);
        parser = new Parser(storage);
        saver.loadData(storage);
    }

    /**
     * Constructs a NewDuke instance with a default file path for data storage at data/duke.txt.
     */
    public NewDuke() {
        this("data/duke.txt");
    }

    /**
     * Gets the response from Duke based on the user input.
     *
     * @param input The user input.
     * @return The response generated by Duke.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.onEnd();
        }
        String action = parser.parseAction(input);
        try {
            String output = "";
            Task t;
            switch (action) {
            case "list":
                return ui.onPrintList("Here are your outstanding tasks!\n" + storage.printList());
            case "mark":
                output = Commands.markCommand(input, storage);
                return ui.onMark(output);
            case "unmark":
                output = Commands.unmarkCommand(input, storage);
                return ui.onUnmark(output);
            case "find":
                output = Commands.findCommand(input, storage);
                return ui.onPrintFind(output);
            case "delete":
                output = Commands.deleteCommand(input, storage);
                return ui.onTaskDeletion(output, storage.size());
            case "todo":
                t = Commands.todosCommand(input, storage);
                return ui.onTaskAddition(storage.addToListOutput(t));
            case "deadline":
                t = Commands.deadlinesCommand(input, storage);
                return ui.onTaskAddition(storage.addToListOutput(t));
            case "event":
                t = Commands.eventsCommand(input, storage);
                return ui.onTaskAddition(storage.addToListOutput(t));
            case "view":
                output = Commands.viewCommand(input, storage);
                return ui.onView(output);
            default:
                return "WHAT DO YOU MEANNN????";
            }
        } catch (DukeExceptions e) {
            return (e.output());
        } finally {
            saver.saveData(storage);
        }

    }
}
