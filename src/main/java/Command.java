import java.util.Scanner;

public class Command {
    
    public static void scan() {
        Scanner scanner = new Scanner(System.in);

        boolean exitScan = false;
        while (!exitScan) {
            String userInput = scanner.nextLine();

            String[] words = userInput.split("\\s+");

            String command = words[0];
            switch (command) {
                case "bye":
                    Bird.goodbye();
                    exitScan = true;
                    break;
                case "list":
                    Bird.list();
                    break;
                default:
                    // 2nd switch is for commands with arguments
                    String arguments = userInput.substring(command.length()+1);
                    switch (command) {
                        case "mark":
                            Bird.markTask(Integer.parseInt(arguments));
                            break; 
                        case "unmark":
                            Bird.unmarkTask(Integer.parseInt(arguments));
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
        scanner.close();
    }

    private static ToDo processToDo(String arguments) {
        return new ToDo(arguments);
    }

    private static Deadline processDeadline(String arguments) {
        String[] parts = arguments.split("/");
        return new Deadline(parts[0], parts[1]);
    }

    private static Event processEvent(String arguments) {
        String[] parts = arguments.split("/");
        return new Event(parts[0], parts[1], parts[2]);
    }
}

