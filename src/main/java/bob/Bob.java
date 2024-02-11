package bob;

import java.util.Scanner;

/**
 * The main ChatBot class which contains the entry point of the program.
 */
public class Bob {

    private BobUI ui;
    private BobStorage storage;
    private BobParser parser;
    private BobTaskList taskList;

    /**
     * Constructor of the Bob class.
     * Requires multiple components for the bot to run.
     *
     * @param ui handles the UI of the bot.
     * @param storage handles saving, loading, and modification of tasks.
     * @param parser parses user input into commands.
     * @param taskList keeps track of user inputted tasks.
     */
    public Bob(BobUI ui, BobStorage storage, BobParser parser, BobTaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.parser = parser;
        this.taskList = taskList;
    }

    /**
     * Main logic of the chatbot.
     */
    public Bob start() {

        try {
            this.taskList.setList(this.storage.loadSavedTasks());
        } catch (BobException.FileAccessError e) {
            this.ui.getErrorText(e);
        } catch (BobException.CorruptedSaveData e) {
            this.ui.getErrorText(e);
        }

        return this;
    }

    /**
     * Init method of the Bot to start the program.
     * Also used by JUnit tests.
     *
     * @returns the Bot class object.
     */
    public static Bob initializeBob() {
        BobUI ui = new BobUI();
        BobStorage storage = new BobStorage(ui);
        BobParser parser = new BobParser();
        BobTaskList taskList = new BobTaskList(storage, ui);

        Bob bob = new Bob(ui, storage, parser, taskList);
        parser.setUi(ui).setTaskList(taskList);

        return bob;
    }

    /**
     * Process input from user.
     *
     * @param input User input in the chat box.
     * @return Chat bot response as String.
     */
    public String getResponse(String input) {
        return this.parser.processInput(input);
    }
}
