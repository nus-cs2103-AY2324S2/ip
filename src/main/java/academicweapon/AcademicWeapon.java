package academicweapon;

import academicweapon.action.Action;
import academicweapon.exceptions.DukeExceptions;
import academicweapon.parser.Parser;
import academicweapon.storage.Storage;
import academicweapon.task.Deadline;
import academicweapon.task.Event;
import academicweapon.task.Task;
import academicweapon.task.TaskList;
import academicweapon.task.Todo;
import academicweapon.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is a task management application that allows users to interact with tasks through a command-line interface.
 * It supports various command for managing tasks such as adding, listing, marking and deleting tasks.
 */
@SuppressWarnings("checkstyle:CommentsIndentation")
public class AcademicWeapon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage
     */
    public AcademicWeapon(String filePath) {
        //String filePath = "./src/main/data/acadList.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke application, handling user interactions through a command-line interface.
     * It processes user commands, updates tasks, and performs necessary  actions based on the commands.
     */
    public String run() {
        StringBuilder sb = new StringBuilder();
        sb.append(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = "";
            try {
                fullCommand = ui.readCommand();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                sb.append(e.getMessage());
                continue;
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                sb.append(e.getMessage());
                continue;
            }

            sb.append(Ui.showLine());
            ArrayList<String> actions = new ArrayList<>();

            try {
                actions = Parser.parse(fullCommand);
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                sb.append(e.getMessage());
                continue;
            }

            assert actions.size() > 0 : "Parsed actions should not be empty";

            Action action = Action.valueOf(actions.get(0));

            switch (action) {
            case FIND:
                ArrayList<String> strLst = this.tasks.findKeyword(actions.get(1));
                if (strLst.isEmpty()) {
                    System.out.println("No tasks containing this keyword");
                    sb.append("No tasks containing this keyword\n");
                    break;
                }
                for (String str : strLst) {
                    System.out.println(str);
                    sb.append(str + "\n");
                }
                break;
            case LIST:
                sb.append(this.tasks.showList());
                break;
            case MARK:
                int indexToMark = Integer.parseInt(actions.get(1));
                Task taskToMark = this.tasks.getTask(indexToMark);
                taskToMark.markAsDone();
                sb.append(this.ui.printMarkDone(taskToMark));
                break;
            case UNMARK:
                int indexToUnmark = Integer.parseInt(actions.get(1));
                Task taskToUnmark = this.tasks.getTask(indexToUnmark);
                taskToUnmark.markAsNotDone();
                sb.append(this.ui.printUnmark(taskToUnmark));
                break;
            case TODO:
                Todo addTodo = new Todo(actions.get(1));
                this.tasks.addTask(addTodo);
                sb.append(this.ui.printAddSuccessful(addTodo, this.tasks.getSize()));
                break;
            case DEADLINE:
                Deadline addDeadline;
                try {
                    addDeadline = new Deadline(actions.get(1), actions.get(2));
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                    sb.append(e.getMessage());
                    continue;
                }

                this.tasks.addTask(addDeadline);
                sb.append(this.ui.printAddSuccessful(addDeadline, this.tasks.getSize()));
                break;
            case EVENT:
                Event addEvent = new Event(actions.get(1), actions.get(2), actions.get(3));
                this.tasks.addTask(addEvent);
                sb.append(this.ui.printAddSuccessful(addEvent, this.tasks.getSize()));
                break;
            case DELETE:
                int index = Integer.parseInt(actions.get(1));
                Task toBeRemoved = this.tasks.removeTask(index);
                sb.append(this.ui.removeSuccessful(toBeRemoved, this.tasks.getSize()));
                break;
            case BYE:
                isExit = true;
                this.storage.saveFile(this.tasks.getList());
                sb.append(this.ui.showBye());
                break;
            default:
                System.out.println("Sorry. I dont understand your command");
                sb.append("Sorry. I dont understand your command\n");
            }
        }
        return sb.toString();
    }

    public String printDeadlineTasks() {
        //System.out.println(this.storage.loadDeadline());
        return this.storage.loadDeadline();
    }

    /**
     * Executes the main logic for handling user input in the GUI.
     * @param input The user input to be processed.
     * @return A String containing the response to the user input.
     */
    public String runGui(String input) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> actions = new ArrayList<>();
        //System.out.println(this.printDeadlineTasks());
        try {
            actions = Parser.parse(input);
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            sb.append(e.getMessage());
            return sb.toString();
        }

        Action action = Action.valueOf(actions.get(0));

        switch (action) {
        case FIND:
            ArrayList<String> strLst = this.tasks.findKeyword(actions.get(1));
            if (strLst.isEmpty()) {
                System.out.println("No tasks containing this keyword");
                sb.append("No tasks containing this keyword\n");
                break;
            }
            for (String str : strLst) {
                System.out.println(str);
                sb.append(str + "\n");
            }
            break;
        case LIST:
            sb.append(this.tasks.showList());
            break;
        case MARK:
            int indexToMark = Integer.parseInt(actions.get(1));
            Task taskToMark = this.tasks.getTask(indexToMark);
            taskToMark.markAsDone();
            sb.append(this.ui.printMarkDone(taskToMark));
            break;
        case UNMARK:
            int indexToUnmark = Integer.parseInt(actions.get(1));
            Task taskToUnmark = this.tasks.getTask(indexToUnmark);
            taskToUnmark.markAsNotDone();
            sb.append(this.ui.printUnmark(taskToUnmark));
            break;
        case TODO:
            Todo addTodo = new Todo(actions.get(1));
            this.tasks.addTask(addTodo);
            sb.append(this.ui.printAddSuccessful(addTodo, this.tasks.getSize()));
            break;
        case DEADLINE:
            Deadline addDeadline;
            try {
                addDeadline = new Deadline(actions.get(1), actions.get(2));
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                sb.append(e.getMessage());
                return sb.toString();
            }

            this.tasks.addTask(addDeadline);
            sb.append(this.ui.printAddSuccessful(addDeadline, this.tasks.getSize()));
            break;
        case EVENT:
            Event addEvent = new Event(actions.get(1), actions.get(2), actions.get(3));
            this.tasks.addTask(addEvent);
            sb.append(this.ui.printAddSuccessful(addEvent, this.tasks.getSize()));
            break;
        case DELETE:
            int index = Integer.parseInt(actions.get(1));
            Task toBeRemoved = this.tasks.removeTask(index);
            sb.append(this.ui.removeSuccessful(toBeRemoved, this.tasks.getSize()));
            break;
        case BYE:
            this.storage.saveFile(this.tasks.getList());
            sb.append(this.ui.showBye());
            break;
        default:
            System.out.println("Sorry. I dont understand your command");
            sb.append("Sorry. I dont understand your command\n");
        }

        return sb.toString();
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
