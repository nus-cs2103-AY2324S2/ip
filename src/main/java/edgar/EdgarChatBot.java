package edgar;


import exceptions.ChatBotException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import commands.Command;
import util.Parser;
import util.Ui;
import util.TaskList;
import util.Storage;
import commands.UserCommand;

import java.io.IOException;

/**
 * EdgarChatBot is a simple chatbot application that manages tasks.
 * It uses TaskList, Storage, Ui, and Parser for various functionalities.
 */
public class EdgarChatBot {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image edgarImage = new Image(this.getClass().getResourceAsStream("/images/timmy.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for EdgarChatBot class.
     * Initializes TaskList, Storage, Ui, and Parser.
     */
    public EdgarChatBot() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            storage.loadFromFile(this.taskList);
        } catch (IOException e) {
            System.out.println("FIX THIS ERROR");
        }
    }

    public UserCommand getResult(String userInput) {
        Command command = this.parser.parseCommand(userInput);
        return command.execute(taskList, ui, storage);
    }
}

    /**
     * Starts the EdgarChatBot application.
     * Load tasks from the file, prints greetings, and processes user commands until "bye" is entered.
     */
    /*public void startBot() {
        try {
            storage.loadFromFile(this.taskList);
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading from file.");
        }
        this.ui.printGreetings();
        String userInput;
        do {
            userInput = this.ui.nextCommand();
            Command c = this.parser.parseCommand(userInput);
            ui.printDivider();
            c.execute(taskList, ui, storage);
            ui.printDivider();
        } while (!userInput.equals("bye"));
    }

    public static void main(String[] args) {
        EdgarChatBot edgar = new EdgarChatBot();
        edgar.startBot();
    }
}
     */

