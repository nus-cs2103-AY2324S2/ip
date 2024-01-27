import java.util.Scanner;
public class TalkingBot {

    private SaveFile saveFile;
    private TaskList taskList;
    private Ui ui;

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

        }
    }

    public static void main(String[] args) {
        String fileName = "./data/talkingbot.txt";
        Ui ui = new Ui();

        try {
            SaveFile saveFile = new SaveFile(fileName);
            Scanner scanner = new Scanner(System.in);
            TaskList taskList = new TaskList();
            try {
                taskList = saveFile.getTasksFromFile();
            } catch (TalkingBotException e) {
                System.out.println("\t" + e);
                System.out.println("\tUsing new file instead...");
                System.out.println(hLine);
            }


            while (true) {
                String entry = scanner.nextLine();
                if (entry.equals("bye")) {
                    saveFile.saveTasksToFile(taskList);
                    break;
                }
                String[] curCommand = entry.split(" ");
                System.out.println(hLine);
                System.out.println(hLine);
            }

            System.out.println(hLine);
            System.out.println(bye);
            System.out.println(hLine);
            scanner.close();
        } catch (TalkingBotException e) {
            System.out.println(e);
        }
    }
}
