package duke;

import duke.exceptions.BaseException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;
import duke.utils.KeyEnum;
import duke.utils.Parser;

import java.io.IOException;

/**
 * Main chatbot class.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Initializes by loading the stored file.
     * @param filePath Path of stored file.
     */
    public Duke(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run(){
        ui.onEnter();
        while (true) {
            try {
                String userInput = ui.getUserInput();
                // use parser to process the userInput
                // the parser object contains all the current user input line information
                Parser parser = new Parser();
                parser.parse(userInput);

                // check for end the session
                if (parser.getCurrentKey().equals(KeyEnum.EXITKEY)){
                    break;
                }

                // continue for the functionality
                switch (parser.getCurrentKey()) {
                    case DEADLINE:
                    case TODO:
                    case EVENT:
                        Task task = tasks.addTask(parser.getInputDetail(), parser.getFrom(), parser.getTo(), parser.getCurrentKey());
                        storage.writeTasksToFile(tasks);
                        ui.onAddSuccess(task, tasks.getNumOfTasks());
                        break;
                    case LIST:
                        ui.showList(tasks);
                        break;
                    case MARK:
                        Task markedTask = tasks.markTaskById(parser.getIndex(), true);
                        storage.writeTasksToFile(tasks);
                        ui.onMarkDone(markedTask);
                        break;
                    case UNMARK:
                        Task unMarkedTask = tasks.markTaskById(parser.getIndex(), false);
                        storage.writeTasksToFile(tasks);
                        ui.onUnmarkDone(unMarkedTask);
                        break;
                    case DELETE:
                        Task deletedTask = tasks.deleteTaskById(parser.getIndex());
                        storage.writeTasksToFile(tasks);
                        ui.onDelete(deletedTask, tasks);
                        break;
                }
            } catch (BaseException e) {
                ui.showErrorMsg(e.getMessage());
            } catch (IOException e) {
                ui.showLoadingError();
            }
        }
        ui.onExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
