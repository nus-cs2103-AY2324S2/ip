import java.util.Scanner;

public class Duke {
    private static String EXITKEY = "bye";
    public static void main(String[] args) {
        String userInput = "";
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while(!userInput.equals(EXITKEY)) {
            userInput = scanner.nextLine();
            System.out.println(userInput);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
