import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
import duke.parser.Parser;
import duke.tasks.TaskList;

import java.util.Scanner;

public class Duke {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNKNOWN_COMMAND_MESSAGE = "The command '%s' is unknown. Please try again!";
    private static final TaskList taskList = new TaskList("/data/tasks.txt");
    private static boolean isOpen = true;

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
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
                case BYE:
                    sayGoodbye();
                    isOpen = false;
                    break;
                case LIST:
                    taskList.toString();
                    break;
                case TODO:
                    taskList.addToDoTask(arguments);
                    break;
                case MARK:
                    taskList.markTask(arguments);
                    break;
                case UNMARK:
                    taskList.unmarkTask(arguments);
                    break;
                case DEADLINE:
                    taskList.addDeadlineTask(arguments);
                    break;
                case EVENT:
                    taskList.addEventTask(arguments);
                    break;
                case DELETE:
                    taskList.deleteTask(arguments);
                    break;
                case INVALID:
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
