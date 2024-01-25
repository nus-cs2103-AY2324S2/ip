import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private Storage storage;
    private static TaskList tasks;
    private static Duke duke;

    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) throws IOException {
        try {
            duke = new Duke("./data/duke.txt");
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }

        Scanner scanner = new Scanner(System.in);
        Ui.printWithLines("Hello! I'm Bob!", "What can I do for you?");

        boolean isRunning = true;
        while (isRunning) {
            String message = scanner.nextLine();
            Ui.parse(tasks, message);
            isRunning = !message.equals("bye");
        }

        Storage.saveCurrentList(tasks); // Assuming this method exists
        Ui.printWithLines("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }

}
