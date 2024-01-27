import talkingbot.command.Command;
import talkingbot.exception.TalkingBotException;
import talkingbot.util.Parser;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class TalkingBot {

    private SaveFile saveFile;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "./data/taskList.txt";

    public TalkingBot(String fileName) {
        this.ui = new Ui();
        this.saveFile = new SaveFile(fileName);
        try {
            this.taskList = this.saveFile.getTasksFromFile();
        } catch (TalkingBotException e) {
            this.ui.printLoadingError(e);
            this.taskList = new TaskList();
        }
    }

    public void runTalkingBot() {
        this.ui.printWelcomeMsg();
        Parser parser =  new Parser();
        while (this.ui.getContinueIter()) {
            Command curCommand = parser.parseCommand();
            this.ui.printLine();
            curCommand.runCommand(this.taskList, this.saveFile, this.ui);
            this.ui.printLine();
        }
    }

    public static void main(String[] args) {
        new TalkingBot(FILE_PATH).runTalkingBot();
    }
}
