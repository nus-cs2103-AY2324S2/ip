import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Ui
        Ui ui = new Ui();

        // Storage
        Storage storage = new Storage();

        // Input reader
        Scanner inputReader = new Scanner(System.in);

        // Greet
        ui.greet();

        // Perform task
        while (true) {
            String task = inputReader.nextLine();
            String[] splitedTask = task.split(" ");

            String taskType = splitedTask[0];
            boolean isEnd = false;

            switch (taskType) {
                case "bye": {
                    isEnd = true;
                    break;
                }
                case "list": {
                    ui.list(storage);
                    break;
                }
                case "mark": {
                    storage.markDone(Integer.parseInt(splitedTask[1]) - 1);
                    ui.mark(storage.getItem(Integer.parseInt(splitedTask[1]) - 1));
                    break;
                }
                case "unmark": {
                    storage.unmarkDone(Integer.parseInt(splitedTask[1]) - 1);
                    ui.mark(storage.getItem(Integer.parseInt(splitedTask[1]) - 1));
                    break;
                }
                case "todo": {
                    Task newTask = new Todo(task);
                    storage.add(newTask);
                    ui.add(newTask, storage);
                    break;
                }
                case "deadline": {
                    // Find the keyword /by, and form description and due date
                    String[] splitedBy = task.split(" /by ");
                    String description = splitedBy[0].substring(9);

                    // Create deadline task
                    Task newTask = new Deadline(description, splitedBy[1]);
                    storage.add(newTask);
                    ui.add(newTask, storage);
                    break;
                }
                case "event": {
                    // Find the keyword /from and /to, and form description, from, and to
                    String[] splitedFrom = task.split(" /from ");
                    String description = splitedFrom[0].substring(6);
                    String[] splitedTo = splitedFrom[1].split(" /to ");

                    // Create event task
                    Task newTask = new Event(description, splitedTo[0], splitedTo[1]);
                    storage.add(newTask);
                    ui.add(newTask, storage);
                    break;
                }
                default: {
                    Task newTask = new Task(task);
                    storage.add(newTask);
                    ui.add(newTask, storage);
                }
            }

            // Loop breaker check
            if (isEnd) {
                break;
            }
        }

        // Exit
        ui.exit();
    }
}
