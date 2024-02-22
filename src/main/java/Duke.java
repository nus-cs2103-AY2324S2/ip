import java.util.Scanner;

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) {

        storage = new Storage("data/duke.txt");
        taskList = new TaskList();
        ui = new Ui();

        Task.resetNextTaskNumber();

        // Load existing tasks from file
        storage.loadTasks(taskList.getTasks());

        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String userInput = scanner.nextLine();

                if (userInput.equals("Bye")) {
                    ui.showByeMessage();
                    storage.writeTasks(taskList.getTasks());
                    break; 
                } else {
                    Parser.parseCommand(userInput, taskList, ui, storage);
                    storage.writeTasks(taskList.getTasks());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close(); 
        }
    }
}
