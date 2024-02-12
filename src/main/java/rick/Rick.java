package rick;

import rick.tasks.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Rick {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static ArrayList<Item> list = new ArrayList<>();
    private static Path filePath = Paths.get("./data/rick.txt");
    private static Path directoryPath = Paths.get("./data");

    public Rick(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RickException e) {
            //to delete
            ui.showLoadingError();
            ui.reply(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.hello();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    this.ui.exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    this.ui.reply(tasks.list());
                } else if (input.startsWith("mark")) {
                    this.ui.reply(tasks.mark(input, storage));
                } else if (input.startsWith("unmark")) {
                    this.ui.reply(tasks.unmark(input, storage));
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    this.ui.reply(tasks.addToList(input, storage));
                } else if (input.startsWith("delete")) {
                    this.ui.reply(tasks.delete(input, storage));
                } else {
                    this.ui.reply("I don't understand what you are saying... ㅜㅜ");
                }
            } catch (RickException e) {
                ui.reply(e.getMessage());
            } catch (Exception e1) {
//                ui.reply(e1.getMessage());
                ui.reply("ERROR: Congratulations! You have input a message that the developer did not expect. Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
                return;
            }
        }

    }
    public static void main(String[] args) {
        new Rick("data/tasks.txt").run();
    }
}
