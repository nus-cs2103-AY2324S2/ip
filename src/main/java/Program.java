import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<String> userInputList = new ArrayList<>(Arrays.asList(userInput));
        userInputList.add("");
        String command = userInputList.get(0).toLowerCase();
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
                    taskNumber = userInputList.get(1);
                    this.taskList.markOrDelete(command, taskNumber, ui);
                    break;
                case "todo": case "deadline": case "event":
                    String task = userInputList.get(1);
                    this.taskList.addTask(command, task, ui);
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
