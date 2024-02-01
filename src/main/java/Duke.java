import java.util.Scanner;

public class Duke {

    public enum TaskType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) {

        // Greet
        Ui.greet();

        // Storage
        Storage storage = new Storage();
        if (storage.setup() == 1) {
            return;
        }

        // Input reader
        Scanner inputReader = new Scanner(System.in);

        // Perform task
        while (true) {
            String task = inputReader.nextLine();
            int responseId = TaskManager.handleTask(task, storage);
            if (responseId == 0) break;
            if (responseId == 2) {
                if (storage.saveChanges() == 1) {
                    return;
                }
            }
        }

        // Exit
        Ui.exit();
    }
}
