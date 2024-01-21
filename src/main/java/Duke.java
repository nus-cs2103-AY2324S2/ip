import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm MichelleBot! \nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon! \\(^-^)/ ");
                break; // Exit the loop and end the program
            }
            System.out.println("Your input is: " + input);
        }
        scanner.close();
    }
}
