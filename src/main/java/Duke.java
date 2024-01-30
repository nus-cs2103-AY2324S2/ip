import java.util.Scanner;
//main class for the bot
public class Duke {
    public static void main(String[] args) {
        System.out.println("Initializing!");

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList taskList = storage.load();

        System.out.println("Hello and welcome! I'm fakegpt\nWhat can I do for you?:");
        Command command;
        do {
            command = Parser.parse(scanner.nextLine());
            command.execute(taskList);
            storage.save(taskList);
        } while (!(command instanceof Command.ByeCommand));
    }
}
