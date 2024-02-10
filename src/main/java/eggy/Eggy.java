package eggy;

import eggy.command.Command;
import eggy.exception.EggyException;
import eggy.parser.Parser;
import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.TaskList;

/**
 * The main class of Eggy chatbot.
 */
public class Eggy {
    /** The name of the chatbot. */
    public static final String NAME = "Eggy";
    /** The file path of the chatbot's storage. */
    private static final String FILE_PATH = "data/tasks.txt";
    /** The user interface of the chatbot. */
    private final Response response;
    /** The storage of the chatbot. */
    private final Storage storage;
    /** The task list of the chatbot. */
    private TaskList tasks;
    /** The exception message when initializing chatbot. */
    private String initExceptionMessage;
    /** The exit status of the chatbot. */
    private boolean isExit = false;

    /**
     * Constructs an Eggy chatbot.
     */
    public Eggy() {
        storage = new Storage(Eggy.FILE_PATH);
        response = new Response();
        try {
            tasks = new TaskList(storage.load());
        } catch (EggyException e) {
            response.setExceptionResponse(e.getMessage());
            this.initExceptionMessage = response.getResponse();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome message of the chatbot.
     *
     * @return The welcome message of the chatbot.
     */
    public String getWelcomeMessage() {
        response.setWelcomeResponse(Eggy.NAME);
        return response.getResponse();
    }

    /**
     * Returns the exception message when initializing chatbot.
     *
     * @return The exception message when initializing chatbot.
     */
    public String getInitExceptionMessage() {
        return this.initExceptionMessage;
    }

    /**
     * Returns the exit status of the chatbot.
     *
     * @return the exit status of the chatbot
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns the response of the chatbot to the given input.
     *
     * @param input The input to the chatbot.
     * @return The response of the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, tasks.getSize());
            command.execute(tasks, response, storage);
            isExit = command.isExit();
        } catch (EggyException e) {
            response.setExceptionResponse(e.getMessage());
        }
        return response.getResponse();
    }
}
