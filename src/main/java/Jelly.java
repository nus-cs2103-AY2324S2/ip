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

        System.out.println(line);

        switch(message){

            case "bye":
                return message;

            case "list":
                System.out.println(list);
                break;

            default:
                list.addTask(message);
        }

        System.out.println(line);
        return message;
    }
}
