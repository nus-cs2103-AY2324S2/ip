package dylanbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents a DylanBot
 */
public class DylanBot {
    private static final String TASKLIST_FILE_PATH = "./data/TaskListData.txt";
    private static final String TAGLIST_FILE_PATH = "./data/TagListData.txt";
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

    /**
     * Creates a DylanBot with the specified save file at the provided filePath
     */
    public DylanBot() {
        this.ui = new Ui();
        this.st = new Storage(TASKLIST_FILE_PATH, TAGLIST_FILE_PATH, ui);
        try {
            ArrayList<Task> taskListData = this.st.loadTaskListData();
            HashMap<String, ArrayList<Integer>> tagListData = this.st.loadTagListData();
            this.tl = new TaskList(taskListData, tagListData, this.ui);
            Ui.print("Loaded data from file");
        } catch (IOException e) {
            System.out.println(e);
            this.tl = new TaskList(ui);
            Ui.print("No data to load, created new file");
        } finally {
            this.ps = new Parser(ui, tl);
        }
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
            st.saveTaskListData(tl);
        } catch (IOException e) {
            ui.displayIoError();
        }
        ui.sendExit();
    }

    /**
     * Returns the response from DylanBot based on the provided user input
     *
     * @param input The user input
     * @return The response from DylanBot
     * @throws DylanBotException If there is an error in the input
     */
    public String getResponse(String input) throws DylanBotException {
        String response;
        try {
            if (input.equals("bye")) {
                this.st.saveTaskListData(this.tl);
                this.st.saveTagListData(this.tl);
                response = "Bye! Hope to see you again soon";
            } else {
                response = ps.parseCommand(input);
            }
            assert !response.isBlank() : "Response cannot be blank";
            return response;
        } catch (DylanBotException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
