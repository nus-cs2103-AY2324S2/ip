import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

import static ui.Ui.printWithSolidLineBreak;


public class Duke {
    // class variables
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) throws DukeException {

        ui = new Ui();
        storage = new Storage();

        try {
            taskList = storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            throw new DukeException("Error loading the list from output.txt");
        }

        // Run main functionality of ByteBuddy
        run();

        // bye
        Ui.printByeMessage();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);

        // repeating user commands
        label:
        while (true) {
            String command = sc.next().toLowerCase();
            String info = sc.nextLine().trim();

            try {
                switch (command) {
                case "bye":
                    break label;
                case "list":
                    taskList.printTaskList();
                    break;
                case "mark":
                    taskList.mark(info);
                    break;
                case "unmark":
                    taskList.unmark(info);
                    break;
                case "delete":
                    taskList.delete(info);
                    break;
                case "todo":
                    taskList.todo(info);
                    break;
                case "deadline":
                    taskList.deadline(info);
                    break;
                case "event":
                    taskList.event(info);
                    break;
                default:
                    throw new DukeException("Sorry but this command does not exist~");
                }
            } catch (DukeException e) {
                printWithSolidLineBreak(e.getMessage());
            }
        }

        // closing
        sc.close();
    }
}
