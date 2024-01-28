import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Jimmy bot = new Jimmy();
        bot.greetUser();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    bot.exit();
                    return;
                case "list":
                    bot.displayList();
                    break;
                default:
                    bot.storeUserInput(userInput);
            }
        }
    }
}
