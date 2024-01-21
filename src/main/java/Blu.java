import java.util.Scanner;

public class Blu {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot("Blu");
        bot.greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            bot.echo(userInput);
        }
        scanner.close();
        bot.exit();
    }
}