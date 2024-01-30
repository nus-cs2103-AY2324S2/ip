import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Program {

    private final String NAME = "Linus";
    private Boolean running;
    private Scanner userInputScanner;
    private Ui ui;
    private TaskList taskList;
    

    public Program() {
        this.running = true;
        this.userInputScanner = new Scanner(System.in);
        this.taskList = new TaskList();
        this.ui = new Ui();

    }

    public void start() {
        File f = this.retrieveFile();
        taskList.loadList(f, ui);
        ui.greeting(NAME);
        ui.print();
        while (this.running) {
            String userInput = this.userInputScanner.nextLine();
            this.readUserInput(userInput);
        }
    }

    public File retrieveFile() {
        File file = new File("./data/linus.txt");
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
            ui.add("Created data folder as none was found");
        }
        try {
            if (file.createNewFile()) {
                ui.add("Created linus.txt to read files from");
            } else {
                ui.add("Retrieving file...");
            }
        } catch (IOException e) {
            ui.add("Could not create file :/");
        }
        ui.print();
        return file;
    }

    private void readUserInput(String input) {
        String[] userInput = input.split(" ", 2);
        String command = userInput[0].toLowerCase();
        String taskNumber;

        try {
            switch (command) {
                case "bye":
                    this.end();
                    break;
                case "list":
                    this.taskList.getList(this.ui);
                    break;
                case "mark": case "unmark": case "delete":
                    if (userInput.length != 2) {
                        throw new DukeCeption("A number is required after writing this command");
                    } else {
                        taskNumber = userInput[1];
                        this.taskList.markOrDelete(command, taskNumber, ui);
                    }
                    break;
                case "todo": case "deadline": case "event":
                if (userInput.length != 2) {
                    throw new DukeCeption("Event description cannot be empty");
                } else {
                    String task = userInput[1];
                    this.taskList.addTask(command, task, ui);
                }
                    break;
                default:
                    throw new DukeCeption("Sorry I don't recognize that command :/");
            }
        } catch (Exception e) {
            ui.add(e.getMessage());
        } finally {
            ui.print();
        }

    }

    private void end() {
        File file = new File("./data/linus.txt");
        taskList.saveList(file);
        ui.goodbye();
        this.running = false;
    }
}
