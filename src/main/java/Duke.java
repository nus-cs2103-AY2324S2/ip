import java.util.ArrayList;


public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        String name = "Georgie";
        // ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        // int taskCounter = 0;

        // TaskReader.loadTasksFromFile(tasks, FILE_PATH);
        TaskReader.loadTasksFromFile(taskList.getTasks(), FILE_PATH);

        Ui.showWelcomeMessage();

        while (true) {
            try {
                String userInput = Ui.getUserInput();
                CommandHandler.handleCommand(userInput, taskList);
                TaskWriter.saveTasksToFile(taskList.getTasks(), FILE_PATH);
                // CommandHandler.handleCommand(userInput, tasks, taskCounter);
                // TaskWriter.saveTasksToFile(tasks, FILE_PATH);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            }
        }
    }
}