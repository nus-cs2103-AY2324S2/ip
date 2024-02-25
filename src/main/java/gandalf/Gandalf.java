package gandalf;

import java.util.Scanner;

/**
 * Main class for the Gandalf chatbot
 */
public class Gandalf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Gandalf() {

    }
    /**
     * A constructor for the chatbot. It takes in two paths as it uses two files for its store/load feature.
     * One file is for loading any existing lists, and another file is meant to be readable in a .txt file
     * @param filePathMeta
     * @param filePathRead
     */

    public Gandalf(String filePathMeta, String filePathRead) {
        ui = new Ui();
        storage = new Storage(filePathMeta, filePathRead);
        try {
            tasks = new TaskList(storage.load());
        } catch (GandalfException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Function to run the chatbot, uses a while-loop to constantly allow the chatbot to receive new inputs
     * Also processes inputs to do various things depending on the command
     */
    public void run() {
        ui.welcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();
            if (input.length() == 0) { //ignore accidental new lines from user
                continue;
            }
            ui.showLine();
            Command c;
            Parser parser = new Parser(input);
            parser.interpret();
            String command = parser.getTaskType();
            try {
                switch (command) {
                case "bye":
                    c = new ByeCommand(command, tasks, storage, ui, scanner);
                    break;
                case "list":
                    c = new ListCommand(command, tasks, storage, ui);
                    break;
                case "delete":
                    c = new DeleteCommand(command, tasks, storage, ui, parser.getTaskName());
                    break;
                case "mark":
                    int taskNumberMark = Integer.parseInt(parser.getTaskName());
                    c = new MarkCommand(command, tasks, storage, ui, taskNumberMark);
                    break;
                case "unmark":
                    int taskNumberUnMark = Integer.parseInt(parser.getTaskName());
                    c = new UnmarkCommand(command, tasks, storage, ui, taskNumberUnMark);
                    break;
                case "find":
                    String keyword = parser.getTaskName();
                    c = new FindCommand(command, tasks, storage, ui, keyword);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    c = new AddCommand(command, tasks, storage, ui, parser.getTaskName(), parser.getStartDate(),
                                        parser.getEndDate());
                    break;
                default:
                    ui.showError("I do not recognize this command, I must check with the head of my order.");
                    ui.showError("If you believe you are right, then check formatting in the user guide.");
                    continue; // Continue to the next iteration of the loop
                }
                c.execute();
                isExit = c.setExit();
            } catch (GandalfException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public String getResponse(String input) {
        return "Gandalf heard: " + input;
    }
    public static void main(String[] args) {
        new Gandalf("docs/gandalfMeta.txt", "docs/gandalfRead.txt").run();
    }
}
