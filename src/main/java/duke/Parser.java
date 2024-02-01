package duke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Parser {

    enum CommandType {LIST, BYE, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT};

    private Scanner scanner;
    private Ui ui;
    private TaskList taskList;
    private Boolean isRunning;
    

    public Parser(TaskList taskList, Ui ui) {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
        this.ui = ui;

    }

    public void run() {
        while (isRunning) {
            this.readUserInput();
        }
    }

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
                    isRunning = false;
                    break;
                case LIST:
                    command = new CommandList(taskList, ui);
                    break;
                case MARK: 
                    command = new CommandMark(taskList, ui);
                    break;
                case UNMARK: 
                    command = new CommandUnmark(taskList, ui);
                    break;
                case DELETE:
                    command = new CommandDelete(taskList, ui);
                    break;
                case TODO: 
                    command = new CommandToDo(taskList, ui);
                    break;
                case DEADLINE:
                    command = new CommandDeadline(taskList, ui);
                    break;
                default: // EVENT
                    command = new CommandEvent(taskList, ui);
                    break;
            }
            command.execute(description);
        } catch (IllegalArgumentException e) {
            ui.print("Sorry I don't recognize that command :/");
        } catch (Exception e) {
            ui.print(e.getMessage());
        }
    }
}
