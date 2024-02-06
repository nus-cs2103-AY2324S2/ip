package fishstock;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates a FishStock object.
 */
class FishStock {
    /**
     * List of keywords to run respective commands.
     */
    protected enum Command {
        INVALID, BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT
    }

    private final String filePath;

    private FishStock(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The main loop for i/o.
     * @param list The TaskList for this loop.
     */
    private static void runIo(TaskList list, Ui ui) {
        String line = "____________________________________________________________";
        String input;
        ArrayList<Task> result;
        boolean hasStopped = false;
        Task task;

        ui.printMsg(line + "\nHello, I'm FishStock.\nI might help if I feel like it.");

        while (!hasStopped) {
            ui.printMsg(line + "\n");
            input = ui.getInput();
            ui.printMsg(line);

            Command command = Parser.parse(input);

            try {
                switch (command) {
                case BYE:
                    hasStopped = true;
                    break;
                case LIST:
                    ui.printMsg("Here are the tasks in your list:");
                    list.printList(ui);
                    break;
                case MARK:
                    task = list.changeMark(command, input);
                    ui.printMsg("Did you actually finish this? \uD83E\uDD14:\n" + "  " + task);
                    break;
                case UNMARK:
                    task = list.changeMark(command, input);
                    ui.printMsg("I knew you didn't finish it! \uD83D\uDE0F:\n" + "  " + task);
                    break;
                case DELETE:
                    task = list.deleteTask(input);
                    ui.printMsg("This task has been removed:\n  " + task + "\n"
                            + "Now you have " + list.getSize() + " task(s) in total.");
                    break;
                case FIND:
                    result = list.findTask(input);
                    ui.printMsg("Here are the matching tasks in your list:");
                    for (int i = 0; i < result.size(); i++) {
                        ui.printMsg((i + 1) + "." + result.get(i));
                    }
                    break;
                case TODO:
                    // Fallthrough
                case DEADLINE:
                    // Fallthrough
                case EVENT:
                    task = list.addTask(command, input);
                    ui.printMsg("This task has been added:\n  " + task + "\n"
                            + "Now you have " + list.getSize() + " task(s) in total.");
                    break;
                default:
                    ui.printMsg("OH NOSE! Wakaranai... :(");
                    break;
                }
            } catch (FishStockException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printMsg("Bye! You'll be back ;)\n" + line);
    }

    /**
     * Main code that calls all other functions in different classes.
     */
    private void run() {
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList list;

        try {
            list = new TaskList(storage.load());
        } catch (FishStockException e) {
            ui.printError(e.getMessage());
            list = new TaskList();
        }

        runIo(list, ui);

        try {
            storage.close();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Entrypoint to FishStock.
     * @param args ignored
     */
    public static void main(String[] args) {
        new FishStock("./data/tasks.txt").run();
    }
}
