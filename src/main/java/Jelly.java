import java.util.*;

public class Jelly {

    private static TaskList list = new TaskList();
    private static String line = "\n-------------------------------------------";

    private static String welcome = "Hello! I'm Jelly\nWhat can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {

        System.out.println(line);
        System.out.println(welcome);
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while(!Read(scanner).equals("bye"));

        System.out.println(farewell);
        System.out.println(line);
    }

    public static String Read(Scanner scanner){

        String message = scanner.nextLine();
        String[] lines = message.split("\\s+");
        String command = lines[0];
        System.out.println(line);

        switch(command){

            case "bye":
                return command;

            case "list":
                System.out.println(list);
                break;

            case "mark":
                list.markTask(Integer.parseInt(lines[1]));
                break;

            case "unmark":
                list.unmarkTask(Integer.parseInt(lines[1]));
                break;

            default:
                list.addTask(command);
        }

        System.out.println(line);
        return command;
    }
}
