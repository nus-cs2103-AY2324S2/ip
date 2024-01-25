import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Greet.greet();

        while (true) {
            String input = scanner.nextLine();

            if (Objects.equals(input, "bye")) {
                break;

            } else {
                Processor.process(input, tasks);

            }
        }

        Exit.exit();
    }
}