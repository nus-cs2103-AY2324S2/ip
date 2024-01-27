import java.util.Scanner;
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
        Parser parser =  new Parser();
        while (true) {
            Command curCommand = parser.parseCommand();
            ui.printLine();
            curCommand.runCommand(taskList, saveFile, ui);
            ui.printLine();
        }
    }

    public static void main(String[] args) {
        new TalkingBot(FILE_PATH).runTalkingBot();
    }
}
