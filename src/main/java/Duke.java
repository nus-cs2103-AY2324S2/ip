import objects.TaskList;
import java.util.Scanner;
import static objects.Processor.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        greet();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            process(input, tasks);
        }

        exit();
    }
}