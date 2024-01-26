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

        String command = words[0];
        
       
        if (!commandSet.contains(command)) { 
            throw new CommandNotFoundException(command);
        }

      
        switch (command) {
            case "bye":
                Bird.goodbye();
                return true;
            case "list":
                Bird.list();
                break;
            default:
                // The logic below is for commands with arguments
                String arguments = "";
                try {
                    arguments = userInput.substring(command.length()+1); 
                } catch (StringIndexOutOfBoundsException e) {
                    throw new ArgumentNotFoundException(command);
                } 
                
            
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
        return false;
    }
    

    private static ToDo processToDo(String arguments) {
        return new ToDo(arguments);
    }

    private static Deadline processDeadline(String arguments) throws InvalidDeadlineFormatException {
        try {
            String[] parts = arguments.split("/by ");
            return new Deadline(parts[0], parts[1]);
        } catch (Exception e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    private static Event processEvent(String arguments) {
        String[] parts = arguments.split("/from ");
        String[] parts2 = parts[1].split("/to ");
        return new Event(parts[0], parts2[0], parts2[1]);
    }
}

