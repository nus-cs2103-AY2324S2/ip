import objects.TaskList;
import view.Exit;
import view.Greeting;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList tasks = Storage.load();
        Scanner scanner = new Scanner(System.in);
        Greeting.display();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            Parser.parse(input, tasks);
            Storage.save(tasks);
        }

        Exit.display();
    }
}