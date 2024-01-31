package Mona;
import java.util.Scanner;

public class Mona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner sc;
    private Parser parser;
    enum Command {
        BYE,
        LIST,
        EVENT,
        TODO,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE
    }
    public Mona(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.sc = new Scanner(System.in);
        this.parser = new Parser();
    }
    public void run() {
        while (true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            Command currCommand;
            try {
                currCommand = parser.getCurrentCommand(inputArray);
            } catch (MonaException e) {
                ui.showError(e.getMessage());
                continue;
            }
            switch (currCommand) {
                case BYE:
                    ui.sayBye();
                    return;
                case LIST:
                    tasks.displayList();
                    break;
                case TODO:
                    String[] details = parser.getDetails(currCommand, input);
                    Task newTask = new Todo(details[0]);
                    tasks.addTask(newTask);
                    ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
                    break;
                case DEADLINE:
                    details = parser.getDetails(currCommand, input);
                    newTask = new Deadline(details[0], details[1]);
                    tasks.addTask(newTask);
                    ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
                    break;
                case EVENT:
                    details = parser.getDetails(currCommand, input);
                    newTask = new Event(details[0], details[1], details[2]);
                    tasks.addTask(newTask);
                    ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
                    break;
                case MARK:
                    Task markedTask = tasks.markTask(Integer.parseInt(inputArray[1]) - 1);
                    ui.showListAfterProgressChange(markedTask);
                    break;
                case UNMARK:
                    Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(inputArray[1]) - 1);
                    ui.showListAfterProgressChange(unmarkedTask);
                    break;
                case DELETE:
                    Task removedTask = tasks.deleteTask(Integer.parseInt(inputArray[1]) - 1);
                    ui.showListAfterQuantityChange(removedTask, tasks.getNumberOfTasks(), false);
                    break;
            }
        }
    }
    public static void main(String[] args) throws MonaException{
        new Mona("data/duke.txt").run();
    }
}
