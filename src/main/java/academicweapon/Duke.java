package academicweapon;

import academicweapon.action.Action;
import academicweapon.exceptions.DukeExceptions;
import academicweapon.parser.Parser;
import academicweapon.storage.Storage;
import academicweapon.task.Task;
import academicweapon.task.TaskList;
import academicweapon.task.Deadline;
import academicweapon.task.Event;
import academicweapon.task.Todo;
import academicweapon.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Duke is a task management application that allows users to interact with tasks through a command-line interface.
 * It supports various command for managing tasks such as adding, listing, marking and deleting tasks.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage
     */
    public Duke() {
        String filePath = "./src/main/data/acadList.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Runs the duke application, handling user interactions through a command-line interface.
     * It processes user commands, updates tasks, and performs necessary  actions based on the commands.
     */
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
            case FIND:
                ArrayList<String> strLst = this.tasks.findKeyword(actions.get(1));
                if (strLst.isEmpty()) {
                    System.out.println("No tasks containing this keyword");
                } else {
                    for (String str: strLst) {
                        System.out.println(str);
                    }
                }
                break;
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
                System.out.println("Sorry. I dont understand your command");
            }
        }
    }

//    /**
//     * Main method to start the Duke application
//     *
//     * @param args Command-line arguments (not used)
//     */
//    public static void main(String[] args) {
//        new Duke("./src/main/data/acadList.txt").run();
//    }
}
