package duke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
// import java.io.IOException;

public class Parser {


    enum CommandType {LIST, BYE, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT};

    // private final String NAME = "Linus";
    // private Boolean running;
    private Scanner scanner;
    private Ui ui;
    private TaskList taskList;
    private Boolean running;
    

    public Parser(TaskList taskList, Ui ui) {
        this.running = true;
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
        this.ui = ui;

    }

    public void run() {
        while (running) {
            this.readUserInput();
        }
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
        Command command;

        try {
            CommandType commandType = CommandType.valueOf(userCommand.toUpperCase());
            switch (commandType) {
                case BYE:
                    command = new CommandBye(taskList, ui);
                    command.execute(description);
                    this.end();
                    running = false;
                    break;
                case LIST:
                    command = new CommandList(taskList, ui);
                    command.execute(description);
                    break;
                case MARK: 
                    command = new CommandMark(taskList, ui);
                    command.execute(description);
                    break;
                case UNMARK: 
                    command = new CommandUnmark(taskList, ui);
                    command.execute(description);
                    break;
                case DELETE:
                    command = new CommandDelete(taskList, ui);
                    command.execute(description);
                    break;
                case TODO: 
                    command = new CommandToDo(taskList, ui);
                    command.execute(description);
                    break;
                case DEADLINE:
                    command = new CommandDeadline(taskList, ui);
                    command.execute(description);
                    break;
                case EVENT:
                    command = new CommandEvent(taskList, ui);
                    command.execute(description);
                    //this.taskList.addTask(userCommand, description, ui);
                    break;
                default:
                    throw new DukeCeption("Sorry I don't recognize that command :/");
            }
        } catch (Exception e) {
            ui.add(e.getMessage());
        }
    }

    private void end() {
        File file = new File("./data/linus.txt");
        taskList.saveList(file);
        ui.goodbye();
        // this.running = false;
    }
}
