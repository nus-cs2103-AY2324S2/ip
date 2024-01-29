import objects.TaskList;
import view.Exit;
import view.Greeting;

import java.util.Scanner;
import static objects.Processor.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Greeting.display();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            process(input, tasks);
        }

        Exit.display();
    }
}