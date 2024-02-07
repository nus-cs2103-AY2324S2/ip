package talkingbot.logic;

import javafx.application.Application;
import javafx.stage.Stage;

import talkingbot.command.Command;
import talkingbot.exception.TalkingBotException;
import talkingbot.gui.Layout;
import talkingbot.util.Parser;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;


/**
 * Class serving as the entry point of the application.
 */
public class TalkingBot extends Application {
    private static final String FILE_PATH = "./data/taskList.txt";
    private SaveFile saveFile;
    private TaskList tasks;
    private Ui ui;
    private Layout layout;

    /**
     * Constructor for the TalkingBot class.
     *
     * @param fileName Path to text file saving the list of tasks.
     */
    public TalkingBot(String fileName) {
        this.ui = new Ui();
        this.saveFile = new SaveFile(fileName);
        this.layout = new Layout();
        try {
            this.tasks = this.saveFile.getTasksFromFile();
        } catch (TalkingBotException e) {
            this.ui.printLoadingError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the application.
     */
//    public void runTalkingBot() {
//        this.ui.printWelcomeMsg();
//        Parser parser = new Parser();
//        while (this.ui.getContinueIter()) {
//            Command curCommand = parser.parseCommand();
//            this.ui.printLine();
//            curCommand.runCommand(this.tasks, this.saveFile, this.ui);
//            this.ui.printLine();
//        }
//    }

    public String process(String text) {
        Parser parser = new Parser();
        Command curCommand = parser.parseCommand(text);
        String response = curCommand.runCommand(this.tasks, this.saveFile, this.ui);
    }

    /**
     * Starts and shows the GUI.
     * @param stage Stage for JavaFX.
     */
    public void start(Stage stage) {
        stage.setScene(this.layout.getScene());
        stage.show();
    }
}
