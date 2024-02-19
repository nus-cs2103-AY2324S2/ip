package dylanbot;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Represents a DylanBot
 */
public class DylanBot {
    private Ui ui;
    private Storage st;
    private Parser ps;
    private TaskList tl;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DylanBotUser.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DylanBotBot.jpeg"));
    private static final String FILE_PATH = "./data/DylanBotData.txt";

    /**
     * Creates a DylanBot with the specified save file at the provided filePath
     *
     * @param filePath The file path of the desired file
     */
    public DylanBot(String filePath) {
        this.ui = new Ui();
        this.st = new Storage(filePath, ui);
        try {
            this.tl = new TaskList(this.st.loadDataFromFile(), ui);
            Ui.print("Loaded data from file");
        } catch (IOException e) {
            System.out.println(e);
            ui.displayIoError();
            this.tl = new TaskList(ui);
            Ui.print("No data to load, created new file");
        }
    }

    public DylanBot() {
        this.ui = new Ui();
        this.st = new Storage(FILE_PATH, ui);
        try {
            this.tl = new TaskList(this.st.loadDataFromFile(), ui);
            Ui.print("Loaded data from file");
        } catch (IOException e) {
            System.out.println(e);
            ui.displayIoError();
            this.tl = new TaskList(ui);
            Ui.print("No data to load, created new file");
        }
        this.ps = new Parser(ui, tl);
    }

    /**
     * Runs DylanBot based on the provided user input
     */
    public void run() {
        ui.sendGreeting();
        Parser ps = new Parser(ui, tl);
        String command;
        try {
            while (!(command = ui.takeInput()).equals("bye")) {
                ps.parseCommand(command);
            }
        } catch (DylanBotException e) {
            ui.displayError(e);
        }
        try {
            st.saveDataToFile(tl);
        } catch (IOException e) {
            ui.displayIoError();
        }
        ui.sendExit();
    }

    public String getResponse(String input) throws DylanBotException {
        String response;
        try {
            if (input.equals("bye")) {
                response = "Bye! Hope to see you again soon";
            } else {
                response = ps.parseCommand(input);
            }
            assert !response.isBlank() : "Response cannot be blank";
            return response;
        } catch (DylanBotException e) {
            return e.getMessage();
        }
    }
}
