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
            Task newTask = new Task(task);

            String choice = splitedTask[0];
            boolean isEnd = false;

            switch (choice) {
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
                default: {
                    storage.add(newTask);
                    ui.add(newTask);
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
