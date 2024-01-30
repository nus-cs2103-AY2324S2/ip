package duke;
import Command.Command;
import java.util.ArrayList;

public class Duke {
    private UI ui;
    private TaskList tasks;

    public Duke() throws DukeException {
        this.ui = new UI();
        try {
            ArrayList<Task> tasks = TaskStorage.loadTasks();
            System.out.println("Loaded tasks: " + tasks);
            this.tasks = new TaskList(tasks);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>()); // Initialize with an empty list in case of error
        }
    }

    public void run() throws DukeException {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(tasks, fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        TaskStorage.saveTasks(tasks.getAllTasks());
        System.out.println("saved");
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
