import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommandHandler {
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    static final Set<Command> commandSet = new HashSet<>(Set.of(
        Command.BYE,
        Command.LIST,
        Command.MARK,
        Command.UNMARK,
        Command.DELETE,
        Command.TODO,
        Command.DEADLINE,
        Command.EVENT
    ));
    
    public static void scan() {
        Scanner scanner = new Scanner(System.in);

        boolean exitScan = false;
        while (!exitScan) {
            String userInput = scanner.nextLine();
            try {
                exitScan = executeCommand(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static boolean executeCommand(String userInput) throws DukeException {
        String[] words = userInput.split("\\s+");

        Command command = Command.valueOf(words[0].toUpperCase());   
       
        if (!commandSet.contains(command)) { 
            throw new CommandNotFoundException(command.name());
        }

        switch (command) {
            case BYE:
                Bird.goodbye();
                return true;
            case LIST:
                Bird.list();
                break;
            default:
                // The logic below is for commands with arguments
                String arguments = "";
                try {
                    arguments = userInput.substring(command.name().length() + 1);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new ArgumentNotFoundException(command.name());
                }
                switch (command) {
                    case MARK:
                        Bird.markTask(Integer.parseInt(arguments));
                        break;
                    case UNMARK:
                        Bird.unmarkTask(Integer.parseInt(arguments));
                        break;
                    case DELETE:
                        Bird.deleteTask(Integer.parseInt(arguments));
                        break;
                    case TODO:
                        Bird.addTask(processToDo(arguments));
                        break;
                    case DEADLINE:
                        Bird.addTask(processDeadline(arguments));
                        break;
                    case EVENT:
                        Bird.addTask(processEvent(arguments));
                        break;
                    default:
                        System.out.println("Error: CommandSet Hashtable contains a command that is not implemented in the switch statement!");
                        break;
                }
        }
        return false;
    }
    

    private static ToDo processToDo(String arguments) {
        return new ToDo(arguments);
    }

    private static Deadline processDeadline(String arguments) throws InvalidDeadlineFormatException {
        try {
            String[] parts = arguments.split("/by ");
            return new Deadline(parts[0], parts[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    private static Event processEvent(String arguments) throws InvalidEventFormatException {
        try {
            String[] parts = arguments.split("/from ");
            String[] parts2 = parts[1].split("/to ");
            return new Event(parts[0], parts2[0], parts2[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidEventFormatException();
        }
    }
}

