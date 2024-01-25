import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Command {
    static final Set<String> commandSet = new HashSet<>(Set.of(
        "bye",
        "list",
        "mark",
        "unmark",
        "delete",
        "todo",
        "deadline",
        "event"
    ));
    
    public static void scan() {
        Scanner scanner = new Scanner(System.in);

        boolean exitScan = false;
        while (!exitScan) {
            String userInput = scanner.nextLine();

            String[] words = userInput.split("\\s+");

            String command = words[0];
            
            boolean commandValid = true;
            try {
                if (!commandSet.contains(command)) {
                    commandValid = false;
                    throw new CommandNotFoundException(command);
                }
            } catch (CommandNotFoundException e) {
                System.out.println(e.getMessage());
            }

            if (commandValid) {
                switch (command) {
                    case "bye":
                        Bird.goodbye();
                        exitScan = true;
                        break;
                    case "list":
                        Bird.list();
                        break;
                    default:
                        // The logic below is for commands with arguments
                        boolean argumentsValid = false;
                        String arguments = "";
                        try {
                            if (userInput.length() <= command.length() + 1) {
                                throw new ArgumentNotFoundException(command);
                            }
                            arguments = userInput.substring(command.length()+1);
                            argumentsValid = true;
                        } catch (ArgumentNotFoundException e) {
                            System.out.println(e.getMessage());
                        } 
                        
                        if (argumentsValid) {
                            switch (command) {
                                case "mark":
                                    Bird.markTask(Integer.parseInt(arguments));
                                    break; 
                                case "unmark":
                                    Bird.unmarkTask(Integer.parseInt(arguments));
                                    break;
                                case "delete":
                                    Bird.deleteTask(Integer.parseInt(arguments));
                                    break;
                                case "todo":
                                    Bird.addTask(processToDo(arguments));
                                    break;
                                case "deadline":
                                    Bird.addTask(processDeadline(arguments));
                                    break;
                                case "event":
                                    Bird.addTask(processEvent(arguments));
                                    break;
                            }  
                        }
                }
            }
        }
        scanner.close();
    }

    private static ToDo processToDo(String arguments) {
        return new ToDo(arguments);
    }

    private static Deadline processDeadline(String arguments) {
        try {
            if (!arguments.contains("/by ")) {
                throw new InvalidDeadlineFormatException();
            }
        } catch (InvalidDeadlineFormatException e) {
            System.out.println(e.getMessage());
        }

        String[] parts = arguments.split("/by ");
        return new Deadline(parts[0], parts[1]);
    }

    private static Event processEvent(String arguments) {
        String[] parts = arguments.split("/from ");
        String[] parts2 = parts[1].split("/to ");
        return new Event(parts[0], parts2[0], parts2[1]);
    }
}

