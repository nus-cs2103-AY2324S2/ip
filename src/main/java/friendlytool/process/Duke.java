package friendlytool.process;

import java.util.Scanner;

import friendlytool.command.CommandTypes;
import friendlytool.command.Parser;

/**
 * Main class for this app.
 */
public class Duke {
    private boolean isActive;
    private TaskList tasks;

    /**
     * Constructs Duke
     */
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
        UI.printInitMsg();
        try {
            Storage.loadTask(tasks);
        } catch (FtException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        while (this.isActive) {
            String input = sc.nextLine();
            try {
                findNextAction(input);
            } catch (FtException e) {
                System.out.println(e.getMessage());
            }
        }
        UI.printByeMsg();
    }

    /**
     * Decides the next action based on the input.
     *
     * @param input user input.
     * @throws FtException
     */
    private void findNextAction(String input) throws FtException {
        if (input.isEmpty()) {
            throw new FtException("Error: Please Type Command");
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
            case FIND:
                TaskFinder.findTask(tasks, input);
                break;
            default:
                throw new FtException("Unknown Command: Please use a correct command");
            }
        } catch (IllegalArgumentException e) {
            throw new FtException("Unknown Command: Please use a correct command");
        }
    }
}

