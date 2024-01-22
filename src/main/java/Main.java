import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lamball lamball = new Lamball();
        boolean active = true;
        lamball.greetingMessage();

        while (active) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // Echo the user's command
            active = lamball.parse(userInput);

        }

        scanner.close();
    }
}
