import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

public class Duke {
    
    private static final String FILE_PATH = System.getProperty("user.home") + File.separator
            + "Downloads" + File.separator + "duke.txt";
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList list;
    
    public Duke(String filePath) {
        this.parser = new Parser();
        this.ui = new Ui();
        this.list = new TaskList(new ArrayList<>());
        this.storage = new Storage(filePath, parser);
        
    }
    public enum Instruction {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, ANYTHING_ELSE
    }
    
    public Instruction getInstr(String input) {
        if (input.toLowerCase().startsWith("list")) {
            return Instruction.LIST;
        } else if (input.toLowerCase().startsWith("mark")) {
            return Instruction.MARK;
        } else if (input.toLowerCase().startsWith("unmark")) {
            return Instruction.UNMARK;
        } else if (input.toLowerCase().startsWith("todo")) {
            return Instruction.TODO;
        } else if (input.toLowerCase().startsWith("deadline")) {
            return Instruction.DEADLINE;
        } else if (input.toLowerCase().startsWith("event")) {
            return Instruction.EVENT;
        } else if (input.toLowerCase().startsWith("delete")) {
            return Instruction.DELETE;
        } else if (input.toLowerCase().startsWith("bye")){
            return Instruction.BYE;
        } else {
            return Instruction.ANYTHING_ELSE;
        }
    }
    
    public void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke(FILE_PATH);
        boolean isRunning = true;
        try {
            duke.storage.loadFile(duke.list);
        } catch (IOException e) {
            duke.ui.loadingError();
        }
    
        duke.ui.greeting();
    
        while(isRunning) {
            String inputFromUser = sc.nextLine();
            Instruction instruction = duke.getInstr(inputFromUser);
        
            switch (instruction) {
            case LIST:
                duke.ui.listing(duke.list);
                break;
            case MARK:
                ui.handleMarkError(inputFromUser, list);
                Task taskToBeMarked = parser.getTaskTobeMarked(inputFromUser, list);
                taskToBeMarked.markDone();
                ui.marking(taskToBeMarked);
                try {
                    storage.changeFileContent(list);
                } catch (IOException e) {
                    this.ui.changingFileError();
                }
                break;
            case UNMARK:
                ui.handleUnmarkError(inputFromUser, list);
                Task taskToBeUnmarked = parser.getTaskToBeUnmarked(inputFromUser, list);
                taskToBeUnmarked.markUndone();
                ui.unmarking(taskToBeUnmarked);
                try {
                    storage.changeFileContent(list);
                } catch (IOException e) {
                    this.ui.changingFileError();
                }
                break;
            case TODO:
                ui.handleTodoError(inputFromUser);
                Task todoTask = parser.createToDo(inputFromUser);
                ui.echo(todoTask, list);
                try {
                    storage.addTaskToFile((todoTask));
                } catch (IOException e) {
                    this.ui.changingFileError();
                }
                break;
            case DEADLINE:
                ui.handleDeadlineError(inputFromUser);
                Task deadlineTask = parser.createDeadline(inputFromUser, ui);
                ui.echo(deadlineTask, list);
                try {
                    storage.addTaskToFile((deadlineTask));
                } catch (IOException e) {
                    this.ui.changingFileError();
                }
                break;
            case EVENT:
                ui.handleEventError(inputFromUser);
                Task eventTask = parser.createEvent(inputFromUser, ui);
                duke.ui.echo(eventTask, duke.list);
                try {
                    duke.storage.addTaskToFile((eventTask));
                } catch (IOException e) {
                    this.ui.changingFileError();
                }
                break;
            case DELETE:
                int indexOfTaskToDelete = Integer.parseInt(inputFromUser.substring(7));
                ui.handleDeleteError(list, inputFromUser,indexOfTaskToDelete);
                Task taskToDelete = duke.parser.getTaskToDelete(inputFromUser, duke.list);
                duke.ui.deleting(taskToDelete, duke.list);
                duke.list.delete(taskToDelete);
                try {
                    duke.storage.changeFileContent(duke.list);
                } catch (IOException e) {
                    this.ui.changingFileError();
                }
                break;
            case BYE:
                duke.ui.bye();
                isRunning = false;
                break;
            default:
                throw new DukeException("Can't understand your instruction");
            }
        }
        sc.close();
    }
    
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH).run();
    }
}