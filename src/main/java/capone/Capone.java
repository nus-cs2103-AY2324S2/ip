package capone;

import capone.commands.Command;
import capone.exceptions.CaponeException;
import capone.exceptions.TaskListCorruptedException;

public class Capone {

    private final TaskList tasks;
    private final TaskStorage storage;
    private final Ui ui;

    public Capone(String taskListPath, String taskListName) {
        this.storage = new TaskStorage(taskListPath, taskListName);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        this.ui.printWelcomeMsg();

        try {
            this.storage.readTasksFromJsonFile(this.tasks);
        } catch (TaskListCorruptedException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            Command command = Parser.processInputs();

            try {
                command.execute(tasks, ui, storage);
            } catch (CaponeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {

        new Capone("./data/","tasks.json").run();
    }
}