package duke;

import java.io.IOException;
import java.util.Objects;

import duke.exceptions.BaseException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;
import duke.utils.Parser;

/**
 * Main chatbot class.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Initializes the chatbot.
     */
    public Duke() {
        this("./data/plaudern.txt");
    }

    /**
     * Initializes by loading the stored file.
     *
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
    public void run() {
        System.out.println(ui.onEnter());
        String response = "";
        do {
            response = getResponse(ui.getUserInput());
        } while (!Objects.equals(response, "Bye. Hope to see you again soon!"
            + "(The window will be closed automatically in 3 seconds)"));
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    /**
     * Gets response from the chatbot.
     * @param userInput Command user entered.
     * @return Response from the chatbot.
     */
    public String getResponse(String userInput) {
        try {
            // use parser to process the userInput
            // the parser object contains all the current user input line information
            Parser parser = new Parser();
            parser.parse(userInput);
            // continue for the functionality
            switch (parser.getCurrentKey()) {
            case EXITKEY:
                return getExitResponse(parser);
            case DEADLINE:
            case TODO:
            case EVENT:
                return getAddResponse(parser);
            case LIST:
                return getListResponse();
            case MARK:
                return getMarkResponse(parser);
            case UNMARK:
                return getUnMarkResponse(parser);
            case DELETE:
                return getDeleteResponse(parser);
            case FIND:
                return getFindResponse(parser);
            default:
                return ui.showErrorMsg("Invalid command");
            }
        } catch (BaseException e) {
            return ui.showErrorMsg(e.getMessage());
        } catch (IOException e) {
            return ui.showLoadingError();
        }
    }

    /**
     * Gets response for adding a task.
     * @param parser parser object
     * @return response for adding a task
     * @throws IOException if the file is not found
     */
    public String getAddResponse(Parser parser) throws IOException {
        Task task = tasks.addTask(parser.getInputDetail(),
            parser.getFrom(), parser.getTo(), parser.getCurrentKey());
        storage.writeTasksToFile(tasks);
        return ui.onAddSuccess(task, tasks.getNumOfTasks());
    }

    /**
     * Gets list response.
     * @return list response
     */
    public String getListResponse() {
        return ui.showList(tasks);
    }

    /**
     * Gets response for marking a task as done.
     * @param parser parser object
     * @return response for marking a task as done
     * @throws IOException if the file is not found
     */
    public String getMarkResponse(Parser parser) throws IOException {
        Task markedTask = tasks.markTaskById(parser.getIndex(), true);
        storage.writeTasksToFile(tasks);
        return ui.onMarkDone(markedTask);
    }

    /**
     * Gets response for unmarking a task.
     * @param parser parser object
     * @return response for unmarking a task
     * @throws IOException if the file is not found
     */
    public String getUnMarkResponse(Parser parser) throws IOException {
        Task unMarkedTask = tasks.markTaskById(parser.getIndex(), false);
        storage.writeTasksToFile(tasks);
        return ui.onUnmarkDone(unMarkedTask);
    }

    /**
     * Gets response for deleting a task.
     * @param parser parser object
     * @return response for deleting a task
     * @throws IOException if the file is not found
     */
    public String getDeleteResponse(Parser parser) throws IOException {
        Task deletedTask = tasks.deleteTaskById(parser.getIndex());
        storage.writeTasksToFile(tasks);
        return ui.onDelete(deletedTask, tasks);
    }

    /**
     * Gets response for finding tasks.
     * @param parser parser object
     * @return response for finding tasks
     */
    public String getFindResponse(Parser parser) {
        TaskList matchedTasks = tasks.findTasks(parser.getInputDetail());
        return ui.showMatchedList(matchedTasks);
    }

    /**
     * Gets response for exiting the chatbot.
     */
    public String getExitResponse(Parser parser) {
        return ui.onExit();
    }

    /**
     * Gets next due tasks.
     * @return next due tasks
     */
    public String getNextDueTasks() {
        return tasks.nextDueTasksToString(2);
    }
}
