import java.util.Scanner;

public class Duke {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNKNOWN_COMMAND_MESSAGE = "The command '%s' is unknown. Please try again!";
    private static final TaskList taskList = new TaskList();
    private static boolean isOpen = true;

    public static void main(String[] args) {
        greet();
        runWaffles();
    }

    private static void runWaffles() {
        Scanner sc = new Scanner(System.in);

        while (isOpen) {
            String userInput = sc.nextLine();
            Parser p = new Parser(userInput);
            Parser.Command command = p.getCommand();
            String arguments = p.getArgument();

            try {
                switch (command) {
                case bye:
                    sayGoodbye();
                    isOpen = false;
                    break;
                case list:
                    taskList.toString();
                    break;
                case todo:
                    taskList.addToDoTask(arguments);
                    break;
                case mark:
                    taskList.markTask(arguments);
                    break;
                case unmark:
                    taskList.unmarkTask(arguments);
                    break;
                case deadline:
                    taskList.addDeadlineTask(arguments);
                    break;
                case event:
                    taskList.addEventTask(arguments);
                    break;
                case delete:
                    taskList.deleteTask(arguments);
                    break;
                case invalid:
                    throw new DukeUnknownCommandException(String.format(UNKNOWN_COMMAND_MESSAGE, p.getUnknownCommand()));
                default:
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }

    public static void greet() {
        String output = String.format("Hello! I'm Waffles!%nWhat can I do for you?");
        System.out.println(output);
    }

    public static void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

}
