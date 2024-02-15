package asher;

import asher.Commands.Command;
import asher.Commands.ExitCommand;
import asher.Commands.Parser;
import asher.Tasks.TaskList;
import asher.Ui.Ui;
import asher.Commands.Storage;

public class Asher {

    private final Ui ui;
    private final TaskList taskList;
    private Storage storage;
    public boolean isExit = false;

    public Asher(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command instanceof ExitCommand) {
                isExit = true;
            }
            return command.execute(ui, taskList, storage);
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }

    public void run() {
        String dataFile = "./taskList.txt";
        storage.getFileContents(dataFile, taskList);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        new Asher(ui, taskList, storage).run();
    }

    /*private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Asher(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser(ui, tasks);
    }

    public void run() {
        String dataFile = "./taskLists.txt";
        storage.getFileContents(dataFile, tasks);
        Scanner scanner = new Scanner(System.in);

        ui.showWelcomeMessage();

        try {
            String input;
            do {
                input = scanner.nextLine();
                parser.parseCommand(input);
            } while (!input.equals("bye"));
        } catch (BotException e) {
            System.out.println("Error: " + e.getMessage());
        }
        storage.writeToFile(tasks);
        ui.showExitMessage();
    }*/
}