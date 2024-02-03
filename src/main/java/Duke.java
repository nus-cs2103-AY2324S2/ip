
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = "";
            try {
                fullCommand = ui.readCommand();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                continue;
            }

            ui.showLine();
            ArrayList<String> actions = new ArrayList<>();

            try {
                actions = Parser.parse(fullCommand);
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                continue;
            }

            Action action = Action.valueOf(actions.get(0));

            switch (action) {
                case LIST:
                    this.tasks.showList();
                    break;
                case MARK:
                    int indexToMark = Integer.parseInt(actions.get(1));
                    Task taskToMark = this.tasks.getTask(indexToMark);
                    taskToMark.markAsDone();
                    this.ui.printMarkDone(taskToMark);
                    break;
                case UNMARK:
                    int indexToUnmark = Integer.parseInt(actions.get(1));
                    Task taskToUnmark = this.tasks.getTask(indexToUnmark);
                    taskToUnmark.markAsNotDone();
                    this.ui.printUnmark(taskToUnmark);
                    break;
                case TODO:
                    Todo addTodo = new Todo(actions.get(1));
                    this.tasks.addTask(addTodo);
                    this.ui.printAddSuccessful(addTodo, this.tasks.getSize());
                    break;
                case DEADLINE:
                    Deadline addDeadline;
                    try {
                        addDeadline = new Deadline(actions.get(1), actions.get(2));
                    } catch (DukeExceptions e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    this.tasks.addTask(addDeadline);
                    this.ui.printAddSuccessful(addDeadline, this.tasks.getSize());
                    break;
                case EVENT:
                    Event addEvent = new Event(actions.get(1), actions.get(2), actions.get(3));
                    this.tasks.addTask(addEvent);
                    this.ui.printAddSuccessful(addEvent, this.tasks.getSize());
                    break;
                case DELETE:
                    int index = Integer.parseInt(actions.get(1));
                    Task toBeRemoved = this.tasks.removeTask(index);
                    this.ui.removeSuccessful(toBeRemoved, this.tasks.getSize());
                    break;
                case BYE:
                    isExit = true;
                    this.storage.saveFile(this.tasks.getList());
                    this.ui.showBye();
                    break;
                default:
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/acadList.txt").run();
    }
}
