package rick;

import java.util.Scanner;

/**
 * A Rick chatbot.
 */
public class Rick {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of the Rick chatbot with specified filePath to store data on hard drive.
     * @param filePath filePath relative to the project directory, where local data is stored.
     */

    public Rick(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RickException e) {
            //to delete
            this.ui.showLoadingError();
            Ui.reply(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs an instance of Rick.
     */
    public void run() {
        Ui.hello();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    Ui.exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    Ui.reply(tasks.list());
                } else if (input.startsWith("mark")) {
                    Ui.reply(tasks.mark(input, storage));
                } else if (input.startsWith("unmark")) {
                    Ui.reply(tasks.unmark(input, storage));
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    Ui.reply(tasks.addToList(input, storage));
                } else if (input.startsWith("delete")) {
                    Ui.reply(tasks.delete(input, storage));
                } else {
                    Ui.reply("I don't understand what you are saying... ㅜㅜ");
                }
            } catch (RickException e) {
                Ui.reply(e.getMessage());
            } catch (Exception e1) {
                Ui.reply("ERROR: Congratulations! "
                        + "You have input a message that the developer did not expect. "
                        + "Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
                return;
            }
        }

    }

    /**
     * Creates a new Rick instance and run it. Executes the programme.
     */
    public static void main(String[] args) {
        new Rick("data/tasks.txt").run();
    }
}
