package duke;

public class Duke {
    private static final String FILE_PATH = "data/duke.txt";

    private static UI ui;
    private static TaskList taskList;
    private static CommandCreator commandCreator;
    private static Storage storage;

    public static void main(String[] args) {
        ui = new UI();
        try {
            storage = new Storage(FILE_PATH);
            taskList = storage.load();
        } catch (DukeException e) {
            ui.printMessage("Error loading task list: " + e.getMessage());
            return;
        }
        commandCreator = new CommandCreator(taskList);
        ui.printWelcomeMessage();
        ui.processCommands(commandCreator, storage);
    }
}
