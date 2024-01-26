import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Processor.greet();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            Processor.process(input, tasks);
        }

        Processor.exit();
    }
}