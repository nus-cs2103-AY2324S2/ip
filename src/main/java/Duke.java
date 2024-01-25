import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Ui
        Ui killua = new Ui();

        // Storage
        Storage storage = new Storage();

        // Input reader
        Scanner inputReader = new Scanner(System.in);

        // Greet
        killua.greet();

        // Echo
        while (true) {
            String task = inputReader.nextLine();
            if (task.equals("bye")) {
                break;
            } else if (task.equals("list")) {
                killua.list(storage);
            } else {
                storage.add(task);
                killua.add(task);
            }
        }

        // Exit
        killua.exit();

    }
}
