package Friday.main;

import java.io.IOException;
import Friday.ui.Ui;
import Friday.storage.Storage;
import Friday.task.TaskList;
import Friday.parser.Parser;
import Friday.task.Todo;
import Friday.task.Deadline;
import Friday.task.Event;

public class Friday {
    private static final String DATA_FILE_PATH = "./src/main/java/data/Friday.txt";
    private static final String horizontalLine = "_".repeat(60);
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Friday() {
        ui = new Ui();
        storage = new Storage(DATA_FILE_PATH);
        parser = new Parser();
        try {
            storage.checkFile();
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }

        try {
            tasks = storage.loadDataFromFile();
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }
    }
    public void run() {
        ui.displayMessage(horizontalLine);
        ui.displayMessage("Hello! I'm Friday");
        ui.displayMessage("What can I do for you?");
        ui.displayMessage(horizontalLine);

        String userInput = ui.getUserInput().trim();
        String category = parser.parseCommand(userInput);
        while (!userInput.equals("bye")) {
            ui.displayMessage(horizontalLine);
            switch (category) {
                case "list":
                    ui.displayMessage("Here are the tasks in your list:");
                    ui.displayTaskList(tasks);
                    break;
                case "mark":
                    tasks.markTask(userInput);
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        ui.displayMessage(e.getMessage());
                    }
                    break;
                case "unmark":
                    tasks.unmarkTask(userInput);
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    Todo t = tasks.addTodo(userInput);
                    try {
                        storage.appendToFile(t.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        ui.displayMessage(e.getMessage());
                    }
                    break;
                case "deadline":
                    Deadline d = tasks.addDeadline(userInput);
                    try {
                        storage.appendToFile(d.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        ui.displayMessage(e.getMessage());
                    }
                    break;
                case "event":
                    Event e = tasks.addEvent(userInput);
                    try {
                        storage.appendToFile(e.toString() + System.lineSeparator());
                    } catch (IOException err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                case "delete":
                    tasks.deleteTask(userInput);
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException err) {
                        ui.displayMessage(err.getMessage());
                    }
                    break;
                default:
                    ui.displayMessage("HUH? What do you mean?");
                    break;
            }
            ui.displayMessage(horizontalLine);
            userInput = ui.getUserInput().trim();
            category = userInput.split(" ")[0];
        }
        ui.displayMessage(horizontalLine);
        ui.displayMessage("Bye. Hope to see you again soon!");
        ui.displayMessage(horizontalLine);
    }

    public static void main(String[] args) {
        new Friday().run();
    }
}