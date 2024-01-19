import java.util.Locale;
import java.util.Scanner;

public class RoeBot {
    private TaskList taskList;
    public enum Command {
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE,
        TODO,
        DEADLINE,
        EVENT
    }
    public RoeBot() {
        this.taskList = new TaskList();
    }

    public void start() {
        printIntroMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            userInput = scanner.nextLine();
            parseUserInput(userInput);
            printHorizontalLine();
        } while (!userInput.equals("bye"));
        printExitMessage();
    }

    public void parseUserInput(String userInput) {
        try {
            String[] parsed = userInput.split(" ", 2);
            Command command = Command.valueOf(parsed[0].toUpperCase());
            switch (command) {
                case MARK:
                    this.taskList.markTask(Integer.parseInt(parsed[1]));
                    break;
                case UNMARK:
                    this.taskList.unmarkTask(Integer.parseInt(parsed[1]));
                    break;
                case DELETE:
                    this.taskList.deleteTask(Integer.parseInt(parsed[1]));
                    break;
                case LIST:
                    this.taskList.listTasks();
                    break;
                case BYE:
                    break;
                default:
                    this.taskList.addTask(userInput);
                    break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("\tInvalid input, Please try again");
        }
    }
    public void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm RoeBot!");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("\t_________________________________________________");
    }
}
