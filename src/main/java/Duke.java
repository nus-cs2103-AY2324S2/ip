import java.util.Scanner;

public class Duke {
    private TaskList taskList = new TaskList();

    private void greet() {
        System.out.println("Hello! I'm Bot\nWhat can I do for you? \n");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon! \n");
    }

    private void echo(String input) {
        System.out.println(input + "\n");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                bot.taskList.displayTasks();
            } else {
                bot.taskList.addTask(input);
            }

            input = sc.nextLine();
        }

        sc.close();

        bot.exit();
    }
}
