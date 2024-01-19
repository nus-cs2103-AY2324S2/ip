import java.util.Scanner;

public class RoeBot {
    private TaskList taskList;
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
            String command = parsed[0];
            switch (command) {
                case "mark":
                    this.taskList.markTask(Integer.parseInt(parsed[1]));
                    break;
                case "unmark":
                    this.taskList.unmarkTask(Integer.parseInt(parsed[1]));
                    break;
                case "delete":
                    this.taskList.deleteTask(Integer.parseInt(parsed[1]));
                    break;
                case "list":
                    this.taskList.listTasks();
                    break;
                case "bye":
                    break;
                default:
                    this.taskList.addTask(userInput);
                    break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
