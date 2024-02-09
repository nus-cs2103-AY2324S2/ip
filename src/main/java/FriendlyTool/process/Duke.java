package FriendlyTool.process;

import FriendlyTool.command.CommandTypes;
import FriendlyTool.command.Parser;

import java.util.Scanner;

/**
 * Main class for this app.
 */
public class Duke {
    private boolean isActive;
    private TaskList tasks;

    public Duke() {
        this.isActive = true;
        this.tasks = new TaskList();
    }
    public static void main(String[] args) {
        Duke ft = new Duke();
        ft.init();
    }

    /**
     * Starts and load saved tasks.
     */
    public void init() {
        UI.initMsg();
        try {
            Storage.loadTask(tasks);
        } catch (ftException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        while (this.isActive) {
            String input = sc.nextLine();
            try {
                nextAction(input);
            } catch (ftException e) {
                System.out.println(e.getMessage());
            }
        }
        UI.byeMsg();
    }

    /**
     * Decides the next action based on the input.
     *
     * @param input user input.
     * @throws ftException
     */
    private void nextAction(String input) throws ftException {
        if (input.isEmpty()) {
            throw new ftException("Error: Please Type Command");
        }
        try {
            CommandTypes command = Parser.parseType(input);
            switch (command) {
                case BYE:
                    this.isActive = false;
                    break;
                case LIST:
                    UI.showList(tasks);
                    break;
                case MARK:
                    tasks.mark(input);
                    Storage.updateTask(tasks);
                    break;
                case UNMARK:
                    tasks.unmark(input);
                    Storage.updateTask(tasks);
                    break;
                case TODO:
                    tasks.addTask(input, CommandTypes.TODO);
                    Storage.updateTask(tasks);
                    break;
                case DEADLINE:
                    tasks.addTask(input, CommandTypes.DEADLINE);
                    Storage.updateTask(tasks);
                    break;
                case EVENT:
                    tasks.addTask(input, CommandTypes.EVENT);
                    Storage.updateTask(tasks);
                    break;
                case DELETE:
                    tasks.deleteTask(input);
                    Storage.updateTask(tasks);
                    break;
                default:
                    throw new ftException("Unknown Command: Please use a correct command");
            }
        }catch (IllegalArgumentException e) {
            throw new ftException("Unknown Command: Please use a correct command");
        }
    }
}

