public class Duke {
    private static TaskList taskList; // Using TaskList to manage tasks
    private static Ui ui;

    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        taskList = new TaskList(); // Initialize the TaskList
        taskList.getTasks().addAll(Storage.loadTasks()); // Load tasks from storage
        new Duke().run();
    }

    public void run() {
        String userInput;

        do {
            ui.showLine();
            userInput = ui.readCommand(); // Use the Ui class to get user input
            try {
                Parser.processCommand(userInput).execute(taskList, ui, userInput);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}
