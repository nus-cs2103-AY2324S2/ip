package badgpt;

import badgpt.exceptions.BadException;
import badgpt.gui.Gui;
import badgpt.util.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The main class for the chatbot program.
 */
public class BadGpt {
    private final String NAME = "BadGPT";
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;
    private TaskList taskList;
    private Ui ui;
    private FileManager fileManager;
    private Gui gui;

    /**
     * Initialises the TaskList, Ui and FileManager instances needed to run the bot. Also takes in the Gui needed to
     * display the chat.
     *
     * @param gui The GUI to be used to display the chat.
     */
    public BadGpt(Gui gui) {
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        taskList = new TaskList(new TasksUi(out, err));
        ui = new Ui(out, err);
        fileManager = new FileManager();
        this.gui = gui;
    }

    /**
     * Runs the bot. The bot gives a greeting and loads the task list saved from the previous session, if there is one.
     * Then, the bot will take in commands until "bye" is entered.
     */
    public void run() {
        ui.sayHi(NAME);

        fileManager.loadFile();
        fileManager.readFile(taskList);
    }

    /**
     * Takes in user input and returns the appropriate response.
     *
     * @param in The user input.
     * @return The response.
     */
    public String parseText(String in) {
        out.reset();
        err.reset();
        try {
            Parser.parse(in, this, taskList);
            return out.toString();
        } catch (BadException e) {
            ui.printException(e);
            return err.toString();
        }
    }

    /**
     * Exits the bot. Save any changes made to the task list and the bot gives a farewell message.
     */
    public void bye() {
        taskList.writeChanges(fileManager);
        ui.sayBye();
        ui.stop();
    }
}
