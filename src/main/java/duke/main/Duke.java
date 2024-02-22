package duke.main;

import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.exception.DukeException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

/**
 * Represents the main logic of the Duke application, a personal assistant chatbot designed to help users manage tasks.
 * It supports operations such as adding, deleting, marking, and listing tasks, among others. The application combines
 * various components for task management, including storage, parsing, user interface, and task list management.
 */
public class Duke {
    
    private static final String FILE_PATH = System.getProperty("user.home") + File.separator
            + "Desktop" + File.separator + "jinni.txt";
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
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND, HELP, ANYTHING_ELSE
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
        } else if(input.toLowerCase().startsWith("help")) {
            return Instruction.HELP;
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
        Command command = new Command();
        try {
            Duke duke = new Duke(FILE_PATH);
            Instruction instruction = duke.getInstr(inputFromUser);
            duke.storage.loadFile(duke.list);
            
            switch (instruction) {
            case LIST:
                response = command.getListResponse(duke.ui, duke.list);
                break;
            case MARK:
                response = command.getMarkResponse(duke.ui, duke.parser, duke.storage, duke.list, inputFromUser);
                break;
            case UNMARK:
                response = command.getUnmarkResponse(duke.ui, duke.parser, duke.storage, duke.list, inputFromUser);
                break;
            case TODO:
                response = command.getTodoResponse(duke.ui, duke.parser, duke.storage, duke.list, inputFromUser);
                break;
            case DEADLINE:
                response = command.getDeadlineResponse(duke.ui, duke.parser, duke.storage, duke.list, inputFromUser);
                break;
            case EVENT:
                response = command.getEventResponse(duke.ui, duke.parser, duke.storage, duke.list, inputFromUser);
                break;
            case DELETE:
                response = command.getDeleteResponse(duke.ui, duke.parser, duke.storage, duke.list, inputFromUser);
                break;
            case FIND:
                response = command.getFindResponse(duke.ui, duke.parser, duke.list, inputFromUser);
                break;
            case HELP:
                response = command.getHelpResponse(duke.ui);
                break;
            case BYE:
                response = duke.ui.bye();
                command.byeResponse();
                break;
            default:
                 response = "Can't understand your instruction. Try typing help";
            }
        } catch (DukeException | IOException e) {
            response = e.getMessage();
        }
        assert response != null : "Response cannot be null";
        return response;
    }
}
