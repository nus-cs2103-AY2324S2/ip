import java.util.Scanner;

public class Blu {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot("Blu");
        bot.greet();

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                bot.displayTasks();
            } else {
                bot.addTask(userInput);
            }
            System.out.print("> ");
            userInput = scanner.nextLine();
        }
        scanner.close();
        bot.exit();
    }
}