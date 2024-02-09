package duke;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        String name = "Georgie";
        TaskList taskList = new TaskList();

        Storage storage = new Storage(FILE_PATH);
        storage.loadTasksFromFile(taskList.getTasks());

        Ui.showWelcomeMessage();

        while (true) {
            try {
                String userInput = Ui.getUserInput();
                CommandHandler.handleCommand(userInput, taskList);
                storage.saveTasksToFile(taskList.getTasks());
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            }
        }
    }
}