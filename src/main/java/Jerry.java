import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "Hello! I'm Jerry.\n" + "Anything I can do for you?";
        System.out.println(message);

        while (true) {
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }

            System.out.println(input);
        }

        scanner.close();
    }
}

