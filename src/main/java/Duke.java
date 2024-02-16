import java.util.List;

import Exceptions.DukeException;
import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Task.TaskType;
import Tasks.ToDo;
import Ui.Parser;
import Ui.Parser.Command;
import Ui.Ui;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Simple chatbot program named kewqgy
 * 
 * @author Tang Yetong
 **/
public class Duke extends Application {

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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput() {
        Label userText = new Label(ui.getUserInput().getText());
        Label dukeText = getResponse(ui.userInput.getText());
        ui.addConversation(userText, dukeText);
    }

    public Label getResponse(String userMsg) {
        String[] userMsgParsed = userMsg.split(" ", 2);
        Command nextCommand = parser.parseUserMsg(userMsgParsed);

        Label dukeText = new Label();

        switch (nextCommand) {
            case LIST:
                dukeText = ui.printList(userTaskList);
                break;
            case MARK:
                if (parser.checkValidMarkCommand(userMsgParsed, userTaskList)) {
                    int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;

                    userTaskList.get(taskInt).setDone(true);
                    storage.updateTask(taskInt, true);

                    dukeText = ui.markTask(userTaskList, taskInt);
                }
                break;
            case UNMARK:
                if (parser.checkValidMarkCommand(userMsgParsed, userTaskList)) {
                    int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;

                    userTaskList.get(taskInt).setDone(false);
                    storage.updateTask(taskInt, false);

                    dukeText = ui.markTask(userTaskList, taskInt);
                }
                break;
            case TODO:
                try {
                    ToDo task = new ToDo(userMsgParsed[1]);
                    userTaskList.add(task);
                    storage.saveTask(userMsgParsed[1], TaskType.T);

                    dukeText = ui.printAddTask(task, userTaskList.size());
                } catch (DukeException e) {
                    dukeText = ui.printError(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    Deadline task = new Deadline(userMsgParsed[1]);
                    userTaskList.add(task);
                    storage.saveTask(userMsgParsed[1], TaskType.D);

                    dukeText = ui.printAddTask(task, userTaskList.size());
                } catch (DukeException e) {
                    dukeText = ui.printError(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    Event task = new Event(userMsgParsed[1]);
                    userTaskList.add(task);
                    storage.saveTask(userMsgParsed[1], TaskType.E);

                    dukeText = ui.printAddTask(task, userTaskList.size());
                } catch (DukeException e) {
                    dukeText = ui.printError(e.getMessage());
                }
                break;
            case DELETE:
                if (parser.checkValidMarkCommand(userMsgParsed, userTaskList)) {
                    int taskInt = Integer.parseInt(userMsgParsed[1]) - 1;

                    userTaskList.remove(taskInt);
                    storage.deleteTask(taskInt);

                    dukeText = ui.printDeleteTask(userTaskList.get(taskInt), userTaskList.size());
                }
                break;
            case FIND:
                dukeText = ui.printTaskKeyword(userTaskList, userMsgParsed[1]);
                break;
            case BYE:
                dukeText = ui.printGoodBye();
                break;
            case UPDATE_TIME:
                String[] updateTimeParsed = userMsg.split(" ", 3);
                if (parser.checkValidMarkCommand(updateTimeParsed, userTaskList)) {
                    int taskInt = Integer.parseInt(updateTimeParsed[1]) - 1;
                    
                    try {
                        userTaskList.get(taskInt).updateTime(updateTimeParsed[2]);;
                        dukeText = ui.printUpdateTime(userTaskList.get(taskInt));
                    } catch (DukeException e) {
                        dukeText = ui.printError(e.getMessage());
                    }
                } 
                break;
            case UNKNOWN:
                dukeText = ui.printError("Unknown Command!");;
                break;
            default:
                break;
        }

        return dukeText;
    }
}
