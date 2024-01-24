import java.util.Scanner;
public class SecretaryW {
    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        String line = "------------------------------------------\n";
        String greeting = "Hello! I'm SecretaryW\n" + "What can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";

        // Greetings
        System.out.println(line + greeting + line);

        // Read user input in the loop
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")){
                break; // Exits loop
            }

            // Echo
            String echo = userInput + "\n";
            System.out.println(line + echo + line);
        }

        // Farewell
        System.out.println(line + farewell + line);
        scanner.close();
    }
}