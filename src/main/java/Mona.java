import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Mona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner sc;
    private Parser parser;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
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
            currCommand = parser.getCurrentCommand(inputArray);
//            try {
//                currCommand = parser.getCurrentCommand(inputArray);
//            } catch (MonaException e) {
//                String response = "  ____________________________________________________________\n"
//                        + "     OOPS!!! I'm sorry, but I don't know what that means :< \n"
//                        + "  ____________________________________________________________\n";
//                System.out.println(response);
//                System.out.println(e.getMessage());
//                continue;
//            }
            switch (currCommand) {
                case BYE:
                    ui.sayBye();
                    return;
                case LIST:
                    tasks.displayList();
                    break;
                case TODO:
                    String details = input.substring(5);
                    Task newTask = new Todo(details);
                    tasks.addTask(newTask);
                    ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
                    break;
                case DEADLINE:
                    details = input.substring(9);
                    String[] parts = details.split(" /by ");
                    newTask = new Deadline(parts[0], LocalDateTime.parse(parts[1], formatter));
                    tasks.addTask(newTask);
                    ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
                    break;
                case EVENT:
                    String[] subDetails = input.substring(6).split(" /from ");
                    String[] startAndEnd = subDetails[1].split(" /to ");
                    newTask = new Event(subDetails[0], LocalDateTime.parse(startAndEnd[0], formatter), LocalDateTime.parse(startAndEnd[1], formatter));
                    tasks.addTask(newTask);
                    ui.showListAfterQuantityChange(newTask, tasks.getNumberOfTasks(), true);
                    break;
                case MARK:
                    Task markedTask = tasks.markTask(Integer.parseInt(inputArray[1]) - 1);
                    String response = "  ____________________________________________________________\n"
                            + "     Nice! I've marked this task as done: \n"
                            + "     " + markedTask + "\n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    break;
                case UNMARK:
                    Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(inputArray[1]) - 1);
                    response = "  ____________________________________________________________\n"
                            + "     OK, I've marked this task as not done yet: \n"
                            + "     " + unmarkedTask + "\n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
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
