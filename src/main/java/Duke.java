import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        try {
            this.storage = new Storage("./data/duke.txt");
            this.taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading/ creating file");
        }
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
            try {
                handler.handle(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }

        sc.close();

        bot.exit();
    }
}
