
/**
 * Duke - Main class that handles user interactions and task management.
 */
public class Duke {
    private static TaskList taskList;
    private static Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        Storage.createFolder();
        Storage.loadFile(taskList.getTasks());
    }

    public void run() {
        ui.printLine();
        ui.showWelcomeMessage();

        while (true) {
            String input = ui.getUserInput();

            if ("bye".equalsIgnoreCase(input)) {
                ui.showByeMessage();
                Storage.saveTasks(taskList.getTasks());
                break;
            }

            handleUserInput(input, parser);
        }
    }

    private static void handleUserInput(String input, Parser parser) {
        parser.handleCommand(input, taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
