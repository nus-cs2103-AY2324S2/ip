package talkingbot.logic;

import talkingbot.command.Command;
import talkingbot.exception.TalkingBotException;
import talkingbot.util.Parser;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class serving as the entry point of the application.
 */
public class TalkingBot {
    private static final String FILE_PATH = "./data/taskList.txt";
    private final SaveFile saveFile;
    private TaskList tasks;
    private final Ui ui;
    private boolean isRunning = true;

    /**
     * Constructs a new TalkingBot object with a given fileName.
     *
     * @param fileName Path to text file saving the list of tasks.
     */
    public TalkingBot(String fileName) {
        this.ui = new Ui();
        this.saveFile = new SaveFile(fileName);
        try {
            this.tasks = this.saveFile.getTasksFromFile();
        } catch (TalkingBotException e) {
            this.ui.getLoadingErrorMsg(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructs a new TalkingBot instance.
     */
    public TalkingBot() {
        this(FILE_PATH);
    }

    /**
     * Processes the input text according and returns a formatted String.
     * @param text String to be processed by the application logic.
     * @return A formatted String.
     */
    public String process(String text) {
        Parser parser = new Parser();
        Command curCommand = parser.parseCommand(text);
        if (curCommand.getCommandArr()[0].equals("bye")) {
            this.isRunning = false;
            assert !this.isRunning : "isRunning must be false";
        }
        return curCommand.runCommand(this.tasks, this.saveFile, this.ui);
    }

    /**
     * Returns a welcome message.
     *
     * @return A welcome message as a String.
     */
    public String getWelcomeMsg() {
        return this.ui.getWelcomeMsg();
    }

    /**
     * Returns whether the bot is running.
     * @return A boolean value.
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * Saves the save file.
     */
    public void save() {
        try {
            this.saveFile.saveTasksToFile(this.tasks);
        } catch (TalkingBotException e) {
            System.out.println(e);
        }
    }
}
