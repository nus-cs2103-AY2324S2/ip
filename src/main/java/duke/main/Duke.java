package duke.main;

import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Task;
import duke.exception.DukeException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Represents the main logic of the Duke application, a personal assistant chatbot designed to help users manage tasks.
 * It supports operations such as adding, deleting, marking, and listing tasks, among others. The application combines
 * various components for task management, including storage, parsing, user interface, and task list management.
 */
public class Duke {
    
    private static final String FILE_PATH = System.getProperty("user.home") + File.separator
            + "Downloads" + File.separator + "duke.txt";
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList list;
    
    /**
     * Constructs a new Duke object with a specified file path for data storage.
     * Initializes the UI, Parser, TaskList, and Storage components of Duke.
     *
     * @param filePath The path of the file where task data is stored.
     */
    public Duke(String filePath) {
        this.parser = new Parser();
        this.ui = new Ui();
        this.list = new TaskList(new ArrayList<>());
        this.storage = new Storage(filePath, parser);
        
    }
    
    /**
     * Initializes a Duke instance with a default file path for data storage.
     */
    public Duke() {
        this.parser = new Parser();
        this.ui = new Ui();
        this.list = new TaskList(new ArrayList<>());
        this.storage = new Storage(FILE_PATH, parser);
    }
    
    /**
     * Enum representing possible instructions that Duke can process.
     */
    public enum Instruction {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND, ANYTHING_ELSE
    }
    
    /**
     * Determines the type of instruction given by the user input.
     *
     * @param input The raw input string from the user.
     * @return The Instruction that corresponds to the user's command.
     */
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
        } else if (input.toLowerCase().startsWith("find")) {
            return Instruction.FIND;
        } else if (input.toLowerCase().startsWith("bye")){
            return Instruction.BYE;
        } else {
            return Instruction.ANYTHING_ELSE;
        }
    }
    
    /**
     * Processes a given input from the user, executing the corresponding task operation and returning a response.
     * This method handles all user commands, managing tasks according to the specified instructions.
     *
     * @param inputFromUser The user's input command.
     * @return A string response indicating the outcome of the command.
     */
    public String getResponse(String inputFromUser) {
        String response;
        
        try {
            Duke duke = new Duke(FILE_PATH);
            Instruction instruction = duke.getInstr(inputFromUser);
            duke.storage.loadFile(duke.list);
            
            switch (instruction) {
            case LIST:
                response = duke.ui.listing(duke.list);
                break;
            case MARK:
                duke.ui.handleMarkError(inputFromUser, duke.list);
                Task taskToBeMarked = duke.parser.getTaskTobeMarked(inputFromUser, duke.list);
                taskToBeMarked.markDone();
                response = duke.ui.marking(taskToBeMarked);
                duke.storage.changeFileContent(duke.list);
                break;
            case UNMARK:
                duke.ui.handleUnmarkError(inputFromUser, duke.list);
                Task taskToBeUnmarked = duke.parser.getTaskToBeUnmarked(inputFromUser, duke.list);
                taskToBeUnmarked.markUndone();
                response = duke.ui.unmarking(taskToBeUnmarked);
                duke.storage.changeFileContent(duke.list);
                break;
            case TODO:
                duke.ui.handleTodoError(inputFromUser);
                Task todoTask = duke.parser.createToDo(inputFromUser);
                response = duke.ui.echo(todoTask, duke.list);
                duke.storage.addTaskToFile((todoTask));
                break;
            case DEADLINE:
                duke.ui.handleDeadlineError(inputFromUser);
                Task deadlineTask = duke.parser.createDeadline(inputFromUser);
                response = duke.ui.echo(deadlineTask, duke.list);
                duke.storage.addTaskToFile((deadlineTask));
                break;
            case EVENT:
                duke.ui.handleEventError(inputFromUser);
                Task eventTask = duke.parser.createEvent(inputFromUser);
                response = duke.ui.echo(eventTask, duke.list);
                duke.storage.addTaskToFile((eventTask));
                break;
            case DELETE:
                int indexOfTaskToDelete = Integer.parseInt(inputFromUser.substring(7));
                duke.ui.handleDeleteError(duke.list,indexOfTaskToDelete);
                Task taskToDelete = duke.parser.getTaskToDelete(inputFromUser, duke.list);
                response = duke.ui.deleting(taskToDelete, duke.list);
                duke.list.delete(taskToDelete);
                duke.storage.changeFileContent(duke.list);
                break;
            case FIND:
                String keyword = duke.parser.getKeywordForFind(inputFromUser);
                response = duke.ui.finding(duke.list, keyword);
                break;
            case BYE:
                response = duke.ui.bye();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> {
                    Platform.exit();
                    System.exit(0);
                });
                delay.play();
                break;
            default:
                 response = "Can't understand your instruction";
            }
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (IOException e) {
            response = "Error accessing storage file: " + e.getMessage();
        }
        assert response != null : "Response cannot be null";
        return response;
    }
}
