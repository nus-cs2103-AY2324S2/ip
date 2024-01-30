package huyang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Huyang {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Huyang() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/huyang_tasks.txt");
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException | TaskException e) {
            ui.printErrorMessage("Error initializing tasks: " + e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Parser.CommandType command = parser.parseCommand(input);
            boolean isChanged = false;

            try {
                switch (command) {
                    case LIST:
                        ui.showTasks(tasks.getTasks());
                        break;
                    case MARK:
                    case UNMARK:
                        isChanged = tasks.markOrUnmarkTask(input, command, ui);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        isChanged = tasks.addTask(input, command, ui);
                        break;
                    case DELETE:
                        isChanged = tasks.deleteTask(input, ui);
                        break;
                    case BYE:
                        isExit = true;
                        break;
                    case UNKNOWN:
                    default:
                        ui.printUnknownCommandMessage();
                        break;
                }
            } catch (TaskException e) {
                ui.printErrorMessage(e.getMessage());
            }

            if (isChanged) {
                try {
                    storage.saveTasks(tasks.getTasks());
                } catch (IOException | TaskException e) {
                    ui.printErrorMessage("Error saving tasks: " + e.getMessage());
                }
            }
        }

        scanner.close();
        ui.farewell();
    }

    public static void main(String[] args) {
        new Huyang().run();
    }
}