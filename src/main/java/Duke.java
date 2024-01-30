import Exceptions.DukeException;
import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Task.TaskType;
import Ui.Parser;
import Ui.Parser.Command;
import javafx.application.Application;
import javafx.stage.Stage;
import Ui.Ui;

import java.util.List;

/**
 * Simple chatbot program named kewqgy
 * 
 * @author Tang Yetong
 **/
public class Duke extends Application {

    @Override
    public void start(Stage stage) {
        Ui ui = new Ui(stage);
        Storage storage = new Storage(ui);
        Parser parser = new Parser();
        List<Task> userTaskList = storage.loadTasks();

        ui.printIntro();

        // String[] userMsg = ui.getUserCommand();
        // Command nextCommand = parser.parseUserMsg(userMsg);

        // while (nextCommand != Command.BYE) {
        //     switch (nextCommand) {
        //         case LIST:
        //             ui.printList(userTaskList);
        //             break;
        //         case MARK:
        //             if (parser.checkValidMarkCommand(userMsg, userTaskList)) {
        //                 int taskInt = Integer.parseInt(userMsg[1]) - 1;

        //                 userTaskList.get(taskInt).setDone(true);
        //                 storage.updateTask(taskInt, true);

        //                 ui.markTask(userTaskList, taskInt);
        //             }
        //             break;
        //         case UNMARK:
        //             if (parser.checkValidMarkCommand(userMsg, userTaskList)) {
        //                 int taskInt = Integer.parseInt(userMsg[1]) - 1;

        //                 userTaskList.get(taskInt).setDone(false);
        //                 storage.updateTask(taskInt, false);

        //                 ui.markTask(userTaskList, taskInt);
        //             }
        //             break;
        //         case TODO:
        //             try {
        //                 ToDo task = new ToDo(userMsg[1]);
        //                 userTaskList.add(task);
        //                 storage.saveTask(userMsg[1], TaskType.T);

        //                 ui.printAddTask(task, userTaskList.size());
        //             } catch (DukeException e) {
        //                 ui.printError(e);
        //             }
        //             break;
        //         case DEADLINE:
        //             try {
        //                 Deadline task = new Deadline(userMsg[1]);
        //                 userTaskList.add(task);
        //                 storage.saveTask(userMsg[1], TaskType.T);

        //                 ui.printAddTask(task, userTaskList.size());
        //             } catch (DukeException e) {
        //                 ui.printError(e);
        //             }
        //             break;
        //         case EVENT:
        //             try {
        //                 Event task = new Event(userMsg[1]);
        //                 userTaskList.add(task);
        //                 storage.saveTask(userMsg[1], TaskType.T);

        //                 ui.printAddTask(task, userTaskList.size());
        //             } catch (DukeException e) {
        //                 ui.printError(e);
        //             }
        //             break;
        //         case DELETE:
        //             if (parser.checkValidMarkCommand(userMsg, userTaskList)) {
        //                 int taskInt = Integer.parseInt(userMsg[1]) - 1;

        //                 userTaskList.remove(taskInt);
        //                 storage.deleteTask(taskInt);

        //                 ui.printDeleteTask(userTaskList.get(taskInt), userTaskList.size());
        //             }
        //             break;
        //         case FIND:
        //             ui.printTaskKeyword(userTaskList, userMsg[1]);
        //             break;
        //         case UNKNOWN:
        //             ui.printUnknownCommand();
        //             break;
        //         default:
        //             break;
        //     }

        //     userMsg = ui.getUserCommand();
        //     nextCommand = parser.parseUserMsg(userMsg);
        // }

        // ui.printGoodBye();
    }
}
