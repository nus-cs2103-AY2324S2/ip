import java.util.Scanner;

public class Duke {
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }

    private void greet() {
        System.out.println("Hello! I'm Bot\nWhat can I do for you? \n");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo(String input) {
        System.out.println(input + "\n");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Handler handler = new Handler(bot.taskList);

        while (!input.equals("bye")) {
            handler.handle(input);
            input = sc.nextLine();
        }

        sc.close();

        bot.exit();
    }
}
