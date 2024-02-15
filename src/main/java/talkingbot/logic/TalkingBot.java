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

    /**
     * Constructor for the TalkingBot class.
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
     * Zero-argument constructor for TalkingBot.
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
        return curCommand.runCommand(this.tasks, this.saveFile, this.ui);
    }
}
