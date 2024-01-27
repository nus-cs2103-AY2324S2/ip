import java.util.*;

public class Jelly {
    public static void main(String[] args) {
        String line = "\n-------------------------------------------";
        String welcome = "Hello! I'm Jelly\nWhat can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(welcome);
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while(!Read(scanner).equals("Bye"));

        System.out.println(farewell);
        System.out.println(line);
    }

    public static String Read(Scanner scanner){

        String message = scanner.nextLine();
        System.out.println(message);
        return message;
    }
}
