import java.io.*;
import oop.Ui;
import oop.Parser;
import oop.Storage;
import oop.TaskList;
import task.Todo;
import task.Task;
import task.Event;
import task.Deadline;

public class Lemona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lemona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greet();
        while (true) {
            String input = ui.readInput();
            String[] parsedInput = Parser.parse(input);
            switch (parsedInput[0]) {
                case ("bye"):
                    ui.bye();
                    System.exit(0);
                    break;
                case ("mark"):
                    tasks.mark(Integer.parseInt(parsedInput[1]));
                    storage.save(tasks);
                    break;
                case ("unmark"):
                    tasks.unmark(Integer.parseInt(parsedInput[1]));
                    storage.save(tasks);
                    break;
                case ("delete"):
                    tasks.delete(Integer.parseInt(parsedInput[1]));
                    storage.save(tasks);
                    break;
                case ("list"):
                    ui.list(tasks);
                    break;
                case ("todo"):
                    Task task = new Todo(parsedInput[1]);
                    tasks.add(task);
                    storage.save(tasks);
                    break;
                case ("deadline"):
                    task = new Deadline(parsedInput[1], parsedInput[2]);
                    tasks.add(task);
                    storage.save(tasks);
                    break;
                case ("event"):
                    task = new Event(parsedInput[1], parsedInput[2], parsedInput[3]);
                    tasks.add(task);
                    storage.save(tasks);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Lemona("data/lemona.txt").run();
    }
}
