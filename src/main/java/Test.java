import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Jimmy bot = new Jimmy();
        bot.greetUser();
        bot.exit();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    bot.exit();
                    return;
                default:
                    System.out.println(userInput);
            }
        }
    }
}
