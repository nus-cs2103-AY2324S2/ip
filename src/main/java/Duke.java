import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm fakegpt\nWhat can I do for you?:");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String userInput = scanner.nextLine();

        Command command = Parser.parse(userInput);
        while (!(command instanceof Command.ByeCommand)) {
            command.execute(taskList);
            command = Parser.parse(scanner.nextLine());
        }

        command.execute(taskList);
//
//                    case "deadline":

//
//                    case "event":
//                        String[] description_rest = userInput.substring(6).split("/from");
//                        String[] from_to = description_rest[1].split(("/to"));
//                        Task.Events newEvent = new Task.Events(description_rest[0].trim(), from_to[0].trim(), from_to[1].trim());
//                        taskList.add(newEvent);
//                        break;
//
//                    default:
//                        System.out.println("I dont know what you are saying, try again");
//
//                }

    }
}
