import java.util.List;

import exceptions.KewgyException;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import tasks.Task.TaskType;
import ui.Parser;
import ui.Ui;
import ui.Parser.Command;

/**
 * Simple chatbot program named kewqgy
 * 
 * @author Tang Yetong
 **/
public class Kewgy extends Application {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private List<Task> userTaskList;

    @Override
    public void start(Stage stage) {
        ui = new Ui(stage);
        storage = new Storage(ui);
        parser = new Parser();
        userTaskList = storage.loadTasks();

        ui.getSendButton().setOnAction(event -> handleUserInput());
        
        ui.printIntro();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kewgy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput() {
        Label userText = new Label(ui.getUserInput().getText());
        Label kewgyText = getResponse(ui.userInput.getText());
        ui.addConversation(userText, kewgyText);
    }

    public Label getResponse(String userMsg) {
        String[] userMsgParsed = userMsg.split(" ", 2);
        Command nextCommand = parser.parseUserMsg(userMsgParsed);
    
        Label kewgyText = new Label();
    
        switch (nextCommand) {
            case LIST:
                kewgyText = getListResponse();
                break;
            case MARK:
                kewgyText = getMarkResponse(userMsgParsed);
                break;
            case UNMARK:
                kewgyText = getUnmarkResponse(userMsgParsed);
                break;
            case TODO:
                kewgyText = getTodoResponse(userMsgParsed);
                break;
            case DEADLINE:
                kewgyText = getDeadlineResponse(userMsgParsed);
                break;
            case EVENT:
                kewgyText = getEventResponse(userMsgParsed);
                break;
            case DELETE:
                kewgyText = getDeleteResponse(userMsgParsed);
                break;
            case FIND:
                kewgyText = getFindResponse(userMsgParsed);
                break;
            case BYE:
                kewgyText = getGoodbyeResponse();
                break;
            case UPDATE_TIME:
                kewgyText = getUpdateTimeResponse(userMsg);
                break;
            case UNKNOWN:
                kewgyText = getUnknownCommandResponse();
                break;
            default:
                break;
        }
    
        return kewgyText;
    }
    
    private Label getListResponse() {
        return ui.printList(userTaskList);
    }
    
    private Label getMarkResponse(String[] userMsgParsed) {
        if (parser.checkValidMarkCommand(userMsgParsed, userTaskList)) {
            int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;
    
            userTaskList.get(taskInt).setDone(true);
            storage.updateTask(taskInt, true);
    
            return ui.markTask(userTaskList, taskInt);
        }
        return new Label();
    }
    
    private Label getUnmarkResponse(String[] userMsgParsed) {
        if (parser.checkValidMarkCommand(userMsgParsed, userTaskList)) {
            int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;
    
            userTaskList.get(taskInt).setDone(false);
            storage.updateTask(taskInt, false);
    
            return ui.markTask(userTaskList, taskInt);
        }
        return new Label();
    }
    
    private Label getTodoResponse(String[] userMsgParsed) {
        try {
            ToDo task = new ToDo(userMsgParsed[1]);
            userTaskList.add(task);
            storage.saveTask(userMsgParsed[1], TaskType.T);
    
            return ui.printAddTask(task, userTaskList.size());
        } catch (KewgyException e) {
            return ui.printError(e.getMessage());
        }
    }
    
    private Label getDeadlineResponse(String[] userMsgParsed) {
        try {
            Deadline task = new Deadline(userMsgParsed[1]);
            userTaskList.add(task);
            storage.saveTask(userMsgParsed[1], TaskType.D);
    
            return ui.printAddTask(task, userTaskList.size());
        } catch (KewgyException e) {
            return ui.printError(e.getMessage());
        }
    }
    
    private Label getEventResponse(String[] userMsgParsed) {
        try {
            Event task = new Event(userMsgParsed[1]);
            userTaskList.add(task);
            storage.saveTask(userMsgParsed[1], TaskType.E);
    
            return ui.printAddTask(task, userTaskList.size());
        } catch (KewgyException e) {
            return ui.printError(e.getMessage());
        }
    }
    
    private Label getDeleteResponse(String[] userMsgParsed) {
        if (parser.checkValidMarkCommand(userMsgParsed, userTaskList)) {
            int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;
    
            userTaskList.remove(taskInt);
            storage.deleteTask(taskInt);
    
            return ui.printDeleteTask(userTaskList.get(taskInt), userTaskList.size());
        }
        return new Label();
    }
    
    private Label getFindResponse(String[] userMsgParsed) {
        return ui.printTaskKeyword(userTaskList, userMsgParsed[1]);
    }
    
    private Label getGoodbyeResponse() {
        return ui.printGoodBye();
    }
    
    private Label getUpdateTimeResponse(String userMsg) {
        String[] updateTimeParsed = userMsg.split(" ", 3);
        if (parser.checkValidMarkCommand(updateTimeParsed, userTaskList)) {
            int taskInt = Integer.parseInt(updateTimeParsed[1]) - 1;
    
            try {
                userTaskList.get(taskInt).updateTime(updateTimeParsed[2]);
                return ui.printUpdateTime(userTaskList.get(taskInt));
            } catch (KewgyException e) {
                return ui.printError(e.getMessage());
            }
        }
        return new Label();
    }
    
    private Label getUnknownCommandResponse() {
        return ui.printError("Unknown Command!");
    }
}
