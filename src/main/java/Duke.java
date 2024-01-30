import objects.TaskList;
import view.Exit;
import view.Greeting;

import java.util.Scanner;
import static objects.Processor.*;

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

            process(input, tasks);
            Storage.save(tasks);
        }

        Exit.display();
    }
}