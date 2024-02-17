import java.util.Scanner;
import java.time.LocalDate;

public class Floofy {
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/duke.txt";
    public Floofy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList();
            storage.loadTasks(tasks);
        } catch (FloofyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] input = parser.parse(userInput);
                switch (input[0]) {
                case "mark":
                    int idx = Integer.parseInt(input[1]) - 1;
                    tasks.markTaskAsDone(idx);
                    ui.showMarkedTask(this.tasks.getTask(idx));
                    storage.saveTasks(tasks);
                    continue;
                case "unmark":
                    int unmarkIdx = Integer.parseInt(input[1]) - 1;
                    tasks.markTaskAsUndone(unmarkIdx);
                    ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
                    storage.saveTasks(tasks);
                    continue;
                case "todo":
                    String todoTask = input[1];
                    ToDos newTodo = new ToDos(todoTask);
                    tasks.addTask(newTodo);
                    ui.showAddedTask(newTodo, tasks.size());
                    storage.saveTasks(tasks);
                    continue;
                case "deadline":
                    String deadlineTask = input[1];
                    LocalDate deadlineBy = LocalDate.parse(input[2]);
                    Deadline newDeadline = new Deadline(deadlineTask, deadlineBy);
                    tasks.addTask(newDeadline);
                    ui.showAddedTask(newDeadline, tasks.size());
                    storage.saveTasks(tasks);
                    continue;
                case "event":
                    String eventTask = input[1];
                    LocalDate eventDateFrom = LocalDate.parse(input[2]);
                    LocalDate eventDateTo = LocalDate.parse(input[3]);
                    Event newEvent = new Event(eventTask, eventDateFrom, eventDateTo);
                    tasks.addTask(newEvent);
                    ui.showAddedTask(newEvent, tasks.size());
                    storage.saveTasks(tasks);
                    continue;
                case "delete":
                    int deleteIdx = Integer.parseInt(input[1]) - 1;
                    Task deletedTask = tasks.getTask(deleteIdx);
                    tasks.deleteTask(deleteIdx);
                    ui.showDeletedTask(deletedTask, tasks.size());
                    storage.saveTasks(tasks);
                    continue;
                case "list":
                    ui.showTaskList(tasks);
                    continue;
                case "bye":
                    ui.showGoodbyeMsg();
                    scanner.close();
                    break loop;
                case "invalid":
                    throw new FloofyException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
                }
            } catch (FloofyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Floofy(FILE_PATH).run();
    }
}
