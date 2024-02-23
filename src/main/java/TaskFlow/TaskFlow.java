package TaskFlow;

import TaskFlow.command.Command;
import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class TaskFlow {

    private static final String FILE_PATH = "./data/taskie.txt";
    private static final String ARCHIVED_FILE_PATH = "./data/archive.txt";
    private Storage storageForTask;
    private Storage storageForArchivedTask;
    private TaskList tasks;
    private TaskList archiveTasks;
    private Ui ui;

    /**
     * Constructs a TaskFlow object with the specified file path for storage.
     */
    public TaskFlow() {
        ui = new Ui();
        storageForTask = new Storage(FILE_PATH);
        storageForArchivedTask = new Storage(ARCHIVED_FILE_PATH);
        try {
            tasks = new TaskList(storageForTask.load());
            archiveTasks = new TaskList(storageForArchivedTask.load());
        } catch (TaskFlowException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the TaskFlow program, processing user commands until the "bye" command is entered.
     * The user is prompted with a welcome message and can interact with the chatbot.
     * Recognized commands include list, mark, unmark, todo, deadline, event, and more.
     * Further carry out the following functions based on the command entered.
     */
    public String run(String userInput) {
        String response = "";
        try {
            Command c = Parser.parse(userInput);
            response += c.execute(tasks, archiveTasks, ui, storageForTask,
                    storageForArchivedTask);
        } catch (TaskFlowException e) {
            response += ui.showError(e.getMessage());
        }
        return response;
    }

    /**
     * A method to show the welcome message.
     *
     * @return A welcome message.
     */
    public String showGreetings() {
        return ui.showWelcomeMsg();
    }
}
