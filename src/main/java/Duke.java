import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm fakegpt\nWhat can I do for you?:");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String userInput = scanner.nextLine();

        Command command = Parser.parse(userInput);
        while (!(command instanceof Command.ByeCommand)) {
            command.execute(taskList);
            command = Parser.parse(scanner.nextLine());
        }

        command.execute(taskList);

    }
}
