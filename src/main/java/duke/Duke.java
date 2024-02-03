package duke;

import duke.task.TaskList;

public class Duke {
    private static TaskList taskList; // Using duke.task.TaskList to manage tasks
    private static Ui ui;

    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        taskList = new TaskList(); // Initialize the duke.task.TaskList
        try {
            taskList.getTasks().addAll(Storage.loadTasks()); // Load tasks from storage
        } catch (DukeException e) {
            System.out.println(e);
        }
        new Duke().run();
    }

    public void run() {
        String userInput;

        do {
            ui.showLine();
            userInput = ui.readCommand(); // Use the duke.Ui class to get user input
            try {
                Parser.processCommand(userInput).execute(taskList, ui, userInput);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}
