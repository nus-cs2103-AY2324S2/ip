import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Jimmy bot = new Jimmy();
        bot.greetUser();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            String[] inputArray = userInput.split(" ");
            String instruction = inputArray[0];
            switch (instruction) {
                case "bye":
                    bot.exit();
                    return;
                case "list":
                    bot.displayTasks();
                    break;
                case "mark":
                    int completeTask = Integer.parseInt(inputArray[1]) - 1;
                    bot.markTaskComplete(completeTask);
                    break;
                case "unmark":
                    int incompleteTask = Integer.parseInt(inputArray[1]) - 1;
                    bot.markTaskIncomplete(incompleteTask);
                    break;
                default:
                    bot.storeUserTask(userInput);
            }
        }
    }
}
