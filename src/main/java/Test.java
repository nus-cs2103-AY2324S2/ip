import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws JimmyException{
        Jimmy bot = new Jimmy();
        bot.greetUser();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            String[] inputArray = userInput.split(" ", 2);
            String instruction;
            String details = "";
            if (inputArray.length > 1) {
                instruction = inputArray[0];
                details = inputArray[1];
            } else {
                instruction = inputArray[0];
            }
            switch (instruction) {
                case "bye":
                    bot.exit();
                    return;
                case "list":
                    bot.displayTasks();
                    break;
                case "mark":
                    int completeTask;
                    try {
                        completeTask = Integer.parseInt(inputArray[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new JimmyException("Please only enter an integer.");
                    }
                    bot.markTaskComplete(completeTask);
                    break;
                case "unmark":
                    try {
                        completeTask = Integer.parseInt(inputArray[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new JimmyException("Please only enter an integer.");
                    }
                    int incompleteTask = Integer.parseInt(inputArray[1]) - 1;
                    bot.markTaskIncomplete(incompleteTask);
                    break;
                default:
                    bot.createNewTask(instruction, details);
            }
        }
    }
}
