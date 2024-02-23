package duke;

import duke.commons.exceptions.DukeException;
import duke.controller.LogicController;
import duke.storage.PersistentStorageHandler;
import duke.task.TaskList;
import duke.ui.AppUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the Duke application.
 * This class handles the initialization and main execution loop of the
 * application,
 * including loading tasks from disk, processing user commands, and exiting the
 * application.
 */
public class Main extends Application {

    private TaskList taskList;
    private PersistentStorageHandler persistentStorageHandler = new PersistentStorageHandler();
    private LogicController logicController = new LogicController();
    private AppUI appUI;

    @Override
    public void start(Stage stage) {
        this.taskList = new TaskList();
        this.logicController = new LogicController();
        this.appUI = new AppUI(stage, logicController);

        initLoadStorage();
        
        logicController.setTaskList(taskList);
    }

    private void initLoadStorage() {
        try {
            if (persistentStorageHandler.ensureTaskFileExists()) {
                taskList = persistentStorageHandler.readTaskFileFromDisc();
                int numTasks = taskList.getNumberTasks();
                String response = "Read existing tasks (" + numTasks + ") from disc";
                appUI.createAgentDialog(response);
            }
        } catch (DukeException e) {
            appUI.createAgentDialog(e.getMessage());
        }
    }
}
