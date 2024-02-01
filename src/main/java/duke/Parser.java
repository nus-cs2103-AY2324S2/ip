package duke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
// import java.io.IOException;

public class Parser {

    // private final String NAME = "Linus";
    // private Boolean running;
    private Scanner scanner;
    private Ui ui;
    private TaskList taskList;
    

    public Parser(TaskList taskList, Ui ui) {
        // this.running = true;
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
        this.ui = ui;

    }

    // public void start() {
    //     //File f = this.retrieveFile();
    //     //taskList.loadList(f, ui);
    //     //ui.greeting(NAME);
    //     //ui.print();
    //     while (this.running) {
    //         String userInput = this.userInputScanner.nextLine();
    //         this.readUserInput(userInput);
    //     }
    // }

    public void readUserInput() {
        String userInput = scanner.nextLine();
        String[] userInputSplit = userInput.split(" ", 2);
        ArrayList<String> userInputList = new ArrayList<>(Arrays.asList(userInputSplit));
        userInputList.add("");
        String userCommand = userInputList.get(0).toLowerCase();
        String description = userInputList.get(1);
        this.parseUserInput(userCommand, description);
    }



    private void parseUserInput(String userCommand, String description) {

        try {
            switch (userCommand) {
                case "bye":
                    this.end();
                    break;
                case "list":
                    this.taskList.getList(this.ui);
                    break;
                case "mark": case "unmark": case "delete":
                    this.taskList.markOrDelete(userCommand, description, ui);
                    break;
                case "todo": case "deadline": case "event":
                    this.taskList.addTask(userCommand, description, ui);
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
        // this.running = false;
    }
}
